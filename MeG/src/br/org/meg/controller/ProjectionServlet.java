package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.meg.dao.EnumTable;
import org.meg.dao.FrameDAO;
import org.meg.dao.GenericModelDAO;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

/**
 * Servlet implementation class Login.
 */
@WebServlet("/projecao")
public class ProjectionServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("Projection");
	
	private final String PROJECTION_VIEW = "projection.jsp";
	private final String GENERATE_PROJECTION_VIEW = "generate-projection.jsp";
	private static final long serialVersionUID = 1L;
	
	// Stores the number of hits the projections got.
	public static int hits = 0;
	
	// Stores the number of projections that missed by less than 10% in the exact number.
	public static int almostHits = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectionServlet() {
		super();
	}

	/**
	 * Redirect to form that will generate projection.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// List options of fields programatically
		session.setAttribute("descriptions", listModel(EnumTable.DESCRIPTION));
		session.setAttribute("sections", listModel(EnumTable.SECTION));
		session.setAttribute("states", listModel(EnumTable.STATE));
		// Redirect to an form
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(GENERATE_PROJECTION_VIEW);
		requestDispatcher.forward(request, response);
		
	}

	/**
	 * Parse user's request and creates a projection based on the year sent by the client. 
	 * Renders a graphic with the projection that was created.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		final int initialProjectionYear = 2013;
		final int finalProjectionYear;
		final int initialYear = 2006;
		final int finalYear = 2012;
		
		List<Frame> frames = new ArrayList<>();
		
		// create a hash that maps attributes of a frame (sent in the request) from String format to Integers
		HashMap<String, Integer> hash = getHash(request);
		
		FrameDAO dao = new FrameDAO();
		
		Description description = new Description(hash.get("description"));
		Section section = new Section(hash.get("section"));
		State state = new State(hash.get("state"));
		frames = dao.getFramesList(initialYear, finalYear, state, section, description);
		
		finalProjectionYear = hash.get("finalYear");
		
		for (int i = initialProjectionYear; i <= finalProjectionYear; i++) {
			createProjection(frames);
		}
		
		session.setAttribute("valores", getValuesList(frames));
		session.setAttribute("anos", getYearsList(frames));
		session.setAttribute("tamanho", frames.size());
		session.setAttribute("titulo", description.getContent());
		session.setAttribute("secao", section.getNome());
		session.setAttribute("estado", state.getNome());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROJECTION_VIEW);
		requestDispatcher.forward(request, response);
		logger.info("Projection created.");
	}

	/**
	 * Each frame is associated with a value. This method return a list of values
	 * corresponding to the list of frames sent as argument.
	 * 
	 * @return a list of values corresponding to the frames sent as argument.
	 */
	private List<Float> getValuesList(List<Frame> frames) {
		List<Float> valuesList = new ArrayList<Float>();
		for (Frame q : frames) {
			valuesList.add(q.getValue());
		}
		return valuesList;
	}

	/**
	 * Each frame is associated with a year. This method return a list
	 * of years (String type) corresponding to the list of frames sent as argument.
	 * 
	 * @return a list of years corresponding to the frames sent as argument.
	 */
	private List<String> getYearsList(List<Frame> frames) {
		List<String> yearsList = new ArrayList<String>();
		for (Frame frame : frames) {
			yearsList.add(String.valueOf(frame.getYear()));
		}
		return yearsList;
	}

	/**
	 * This method is based on the moving average statistics technique to calculate a frame's projection.
	 * It calculates the projection of the next year after the last frame in the List sent as argument.
	 * It also adds to the list the projection frame that was created.
	 * 
	 * @return the value of the projection.
	 */
	private float createProjection(List<Frame> frames) {
		// a new frame (projection) will be added so array's size is frame's size plus one
		float movingAverage[] = new float[frames.size() + 1];
		float projections[] = new float[9];
		float biggestProjection = 0.0f;
		int currentIndex = 0;
		Frame projection = new Frame();
		projection.setYear(getLastFrame(frames).getYear() + 1);
		projection.setDescription(getLastFrame(frames).getDescription());
		projection.setState(getLastFrame(frames).getState());
		projection.setSection(getLastFrame(frames).getSection());
		
		if (isFunctionAscending(frames) || isFunctionDescending(frames)) {
			projection.setValue((int) Math.ceil(getLastFrame(frames).getValue() 
					+ this.getGrowthAverage(frames)));
		} else {
			for (float alfa = 0.1f; alfa < 1.0f; alfa += 0.1) {
				movingAverage[0] = frames.get(0).getValue();
				for (int i = 0; i < frames.size(); i++) {
					movingAverage[i + 1] = movingAverage[i] 
							+ alfa * (frames.get(i).getValue() - movingAverage[i]);
				}
				projections[currentIndex++] = movingAverage[frames.size()];
			}
			biggestProjection = projections[projections.length - 1];
			projection.setValue((int) Math.ceil(biggestProjection));
		}
		
		frames.add(projection);
		return getLastFrame(frames).getValue();
	}
	
	/**
	 * Identifies if the function described by the list of frames sent as argument is ascending.
	 * 
	 * @return a boolean stating if the function is ascending.
	 */
	private boolean isFunctionAscending(List<Frame> frames) {
		for (int i = frames.size() - 2; i < frames.size(); i++) {
			if (frames.get(i).getValue() < frames.get(i - 1).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Identifies if the function described by the list of frames sent as argument is descending.
	 * 
	 * @return a boolean stating if the function is descending.
	 */
	private boolean isFunctionDescending(List<Frame> frames) {
		for (int i = frames.size() - 2; i < frames.size(); i++) {
			if (frames.get(i).getValue() > frames.get(i - 1).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Calculates the average growth of the list of frames sent as argument.
	 * 
	 * @return a float value representing the growth average.
	 */
	private float getGrowthAverage(List<Frame> frames) {
		float media = 0.0f;
		for (int i = 1; i < frames.size(); i++) {
			media += frames.get(i).getValue() - frames.get(i - 1).getValue();
		}
		media = 0.3f * media/frames.size();
		return media;
	}
	
	/**
	 * Creates a hash that maps the attributes sent in the user's request in String type to Integer type.
	 * 
	 * @return a hash mapping String keys into Integer values.
	 */
	private HashMap<String, Integer> getHash(HttpServletRequest request) {
		HashMap<String, Integer> hash = new HashMap<>();
		final String[] attributesFrame = {"description", "section", "state", "finalYear"};
		
		for(String iterator : attributesFrame) {
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
	}
	
	/**
	 * Gets the last element of the list of frames sent as argument.
	 * 
	 * @return last frame of the list.
	 */
	private Frame getLastFrame(List<Frame> framesList) {
		Frame lastElement = framesList.get(framesList.size() - 1);
		return lastElement;
	}
	
	private List<Object> listModel(EnumTable typeOfModel) {
		GenericModelDAO DAO = new GenericModelDAO(typeOfModel);
		List<Object> list = DAO.listAll();
		return list;
	}
}

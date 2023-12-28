package project.servlet;

import project.dao.CharacterDao;
import project.model.Character;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageCharacter")
public class CharacterServlet extends HttpServlet {

	private CharacterDao characterDao;

	@Override
	public void init() {
		characterDao = CharacterDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "list";
		}

		switch (action) {
		case "condition":
			listCharactersByCondition(request, response);
			break;
		case "list":
			listCharacters(request, response);
			break;
		case "searchById":
			searchCharacterById(request, response);
			break;
		case "searchByLastName":
			searchCharactersByLastName(request, response);
			break;
		case "delete":
			deleteCharacter(request, response);
			break;
		default:
			listCharacters(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
//                case "create":
//                    createCharacter(request, response);
//                    break;
			case "update":
				updateCharacterLastName(request, response);
				break;
			}
		}
	}

	private void listCharactersByCondition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		List<Character> characters;
		try {
			characters = characterDao.getCharactersByCondition(firstName,lastName,email);
			request.setAttribute("characters", characters);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error retrieving characters: " + e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("manageCharacterCondition.jsp");
		dispatcher.forward(request, response);
	}

	private void listCharacters(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Character> characters;
		try {
			characters = characterDao.getAllCharacters();
			request.setAttribute("characters", characters);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error retrieving characters: " + e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("manageCharacter.jsp");
		dispatcher.forward(request, response);
	}

	private void searchCharacterById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String characterIdString = request.getParameter("characterId");
		if (characterIdString != null && !characterIdString.isEmpty()) {
			int characterId = Integer.parseInt(characterIdString);
			try {
				Character character = characterDao.getCharacterByCharacterID(characterId);
				if (character != null) {
					request.setAttribute("character", character);
				} else {
					request.setAttribute("error", "Character not found with ID: " + characterId);
					// Log the event when a character is not found
					System.out.println("Character not found for ID: " + characterId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// Improved error logging
				System.err.println("SQLException while searching for character by ID: " + characterId + ". Error: "
						+ e.getMessage());
				request.setAttribute("error", "Error searching character by ID: " + e.getMessage());
			}
		} else {
			request.setAttribute("error", "Please provide a valid Character ID.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("manageCharacter.jsp");
		dispatcher.forward(request, response);
	}

	private void searchCharactersByLastName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			try {
				List<Character> characters = characterDao.getCharactersByLastName(lastName);
				if (!characters.isEmpty()) {
					request.setAttribute("characters", characters);
				} else {
					request.setAttribute("error", "No characters found with Last Name: " + lastName);
					// Log the event when no characters are found
					System.out.println("No characters found with Last Name: " + lastName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// Improved error logging
				System.err.println("SQLException while searching for characters by Last Name: " + lastName + ". Error: "
						+ e.getMessage());
				request.setAttribute("error", "Error searching characters by Last Name: " + e.getMessage());
			}
		} else {
			request.setAttribute("error", "Please provide a Last Name to search.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("manageCharacter.jsp");
		dispatcher.forward(request, response);
	}

	private void createCharacter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		try {
			Character newCharacter = new Character(null, firstName, lastName);
			characterDao.create(newCharacter);
			request.setAttribute("message", "Character created successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error creating Character: " + e.getMessage());
		}
		listCharacters(request, response);
	}

	private void updateCharacterLastName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String characterIdString = request.getParameter("characterId");
		String lastName = request.getParameter("lastName");

		if (characterIdString != null && !characterIdString.isEmpty()) {
			int characterId = Integer.parseInt(characterIdString);
			try {
				Character character = characterDao.getCharacterByCharacterID(characterId);
				if (character != null) {
					character = characterDao.updateCharacterLastName(character, lastName);
					request.setAttribute("message", "Character's Last Name updated successfully.");
				} else {
					request.setAttribute("error", "Character not found with ID: " + characterId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error updating Character's Last Name: " + e.getMessage());
			}
		} else {
			request.setAttribute("error", "Please provide a valid Character ID.");
		}
		listCharacters(request, response);
	}

	private void deleteCharacter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String characterIdString = request.getParameter("characterId");

		if (characterIdString != null && !characterIdString.isEmpty()) {
			int characterId = Integer.parseInt(characterIdString);
			try {
				Character character = characterDao.getCharacterByCharacterID(characterId);
				if (character != null) {
					characterDao.delete(character);
					request.setAttribute("message", "Character deleted successfully.");
				} else {
					request.setAttribute("error", "Character not found with ID: " + characterId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error deleting Character: " + e.getMessage());
			}
		} else {
			request.setAttribute("error", "Please provide a valid Character ID.");
		}
		listCharacters(request, response);
	}
}

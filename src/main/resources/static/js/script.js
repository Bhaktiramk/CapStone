 // Get form elements
var loginForm = document.getElementById("loginForm");
var usernameInput = document.getElementById("username");
var passwordInput = document.getElementById("password");
var errorMessage = document.getElementById("errorMessage");

// Add form submit event listener
loginForm.addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent form submission

  // Perform form validation
  var username = usernameInput.value.trim();
  var password = passwordInput.value.trim();

  if (username === "") {
    showError("Please enter a username.");
    return;
  }

  if (password === "") {
    showError("Please enter a password.");
    return;
  }

  if (password.length < 6) {
    showError("Password must be at least 6 characters long.");
    return;
  }

  // Clear error message and submit the form
  showError("");
  loginForm.submit();
});

// Function to display error messages
function showError(message) {
  errorMessage.textContent = message;
}

// Get form elements
var contactForm = document.querySelector("form");
var firstNameInput = document.getElementById("firstName");
var lastNameInput = document.getElementById("lastName");
var emailInput = document.getElementById("email");
var phoneInput = document.getElementById("phone");
var messageInput = document.getElementById("message");

// Add form submit event listener
contactForm.addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent form submission

  // Perform form validation
  var firstName = firstNameInput.value.trim();
  var lastName = lastNameInput.value.trim();
  var email = emailInput.value.trim();
  var phone = phoneInput.value.trim();
  var message = messageInput.value.trim();

  if (firstName === "") {
    showError("Please enter your first name.");
    return;
  }

  if (lastName === "") {
    showError("Please enter your last name.");
    return;
  }

  if (email === "") {
    showError("Please enter your email address.");
    return;
  }

  if (!isValidEmail(email)) {
    showError("Please enter a valid email address.");
    return;
  }

  if (phone === "") {
    showError("Please enter your phone number.");
    return;
  }

  if (message === "") {
    showError("Please enter your message.");
    return;
  }

  // Clear error message and submit the form
  showError("");
  contactForm.submit();
});

// Function to display error messages
function showError(message) {
  var errorMessage = document.createElement("div");
  errorMessage.className = "error-message";
  errorMessage.textContent = message;
  var existingErrorMessage = document.querySelector(".error-message");
  if (existingErrorMessage) {
    contactForm.removeChild(existingErrorMessage);
  }
  contactForm.appendChild(errorMessage);
}

// Function to validate email address using regular expression
function isValidEmail(email) {
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}



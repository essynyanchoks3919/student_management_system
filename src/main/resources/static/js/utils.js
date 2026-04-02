/**
 * Utility Functions for University SMS System
 * Common functions used across the application
 */

/**
 * Show Confirmation Dialog
 * @param {string} title - Dialog title
 * @param {string} message - Dialog message
 * @param {function} callback - Callback function when confirmed
 */
function showConfirmation(title, message, callback) {
  const modal = document.getElementById('confirmationModal');
  document.getElementById('confirmationTitle').textContent = title;
  document.getElementById('confirmationMessage').textContent = message;
  
  // Store callback for execution
  window.confirmCallback = callback;
  
  modal.classList.remove('hidden');
}

/**
* Close Confirmation Modal
*/
function closeConfirmationModal() {
  document.getElementById('confirmationModal').classList.add('hidden');
  window.confirmCallback = null;
}

/**
* Execute Confirmed Action
*/
function confirmAction() {
  if (window.confirmCallback) {
      window.confirmCallback();
  }
  closeConfirmationModal();
}

/**
* Show Error Modal
* @param {string} title - Error title
* @param {string} message - Error message
*/
function showError(title, message) { git
  const modal = document.getElementById('errorModal');
  document.getElementById('errorTitle').textContent = title;
  document.getElementById('errorMessage').textContent = message;
  modal.classList.remove('hidden');
}
  // Function to close error modal
function closeErrorModal() {
  document.getElementById('errorModal').classList.add('hidden');
}
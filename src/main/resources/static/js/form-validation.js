/**
 * Client-Side Form Validation
 * Validates forms before submission
 */

/**
 * Validate Email Address
 * @param {string} email - Email to validate
 * @returns {boolean} - True if valid
 */
function isValidEmail(email) {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
}

/**
* Validate Phone Number
* @param {string} phone - Phone number to validate
* @returns {boolean} - True if valid
*/
function isValidPhone(phone) {
  const regex = /^[+]?[(]?[0-9]{3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,6}$/;
  return regex.test(phone.replace(/\s/g, ''));
}

/**
* Validate Required Field
* @param {string} value - Value to validate
* @returns {boolean} - True if not empty
*/
function isRequired(value) {
  return value && value.trim().length > 0;
}

/**
* Validate Field Length
* @param {string} value - Value to check
* @param {number} min - Minimum length
* @param {number} max - Maximum length
* @returns {boolean} - True if valid
*/
function isValidLength(value, min = 0, max = Infinity) {
  const length = value ? value.length : 0;
  return length >= min && length <= max;
}

/**
* Validate Date Format (YYYY-MM-DD)
* @param {string} dateString - Date to validate
* @returns {boolean} - True if valid
*/
function isValidDate(dateString) {
  const regex = /^\d{4}-\d{2}-\d{2}$/;
  if (!regex.test(dateString)) return false;
  
  const date = new Date(dateString);
  return date instanceof Date && !isNaN(date);
}

/**
* Show Field Error
* @param {HTMLElement|string} element - Input element or element ID
* @param {string} message - Error message
*/
function showFieldError(element, message) {
  if (typeof element === 'string') {
      element = document.getElementById(element);
  }
  
  if (!element) return;
  
  // Add error class
  element.classList.add('border-red-500', 'focus:ring-red-500');
  element.classList.remove('border-gray-300', 'focus:ring-indigo-500');
  
  // Create error message element
  let errorEl = element.nextElementSibling;
  if (!errorEl || !errorEl.classList.contains('form__error')) {
      errorEl = document.createElement('p');
      errorEl.className = 'form__error';
      element.parentNode.insertBefore(errorEl, element.nextSibling);
  }
  
  errorEl.textContent = message;
  errorEl.style.display = 'block';
}

/**
* Clear Field Error
* @param {HTMLElement|string} element - Input element or element ID
*/
function clearFieldError(element) {
  if (typeof element === 'string') {
      element = document.getElementById(element);
  }
  
  if (!element) return;
  
  // Remove error class
  element.classList.remove('border-red-500', 'focus:ring-red-500');
  element.classList.add('border-gray-300', 'focus:ring-indigo-500');
  
  // Hide error message
  let errorEl = element.nextElementSibling;
  if (errorEl && errorEl.classList.contains('form__error')) {
      errorEl.style.display = 'none';
  }
}

/**
* Validate Form Element
* @param {HTMLElement} element - Form element to validate
* @param {Object} rules - Validation rules
* @returns {boolean} - True if valid
*/
function validateElement(element, rules) {
  const value = element.value;
  const type = rules.type || 'text';
  
  // Check required
  if (rules.required && !isRequired(value)) {
      showFieldError(element, `${rules.label || 'This field'} is required`);
      return false;
  }
  
  if (!value) {
      clearFieldError(element);
      return true;
  }
  
  // Type-specific validation
  switch (type) {
      case 'email':
          if (!isValidEmail(value)) {
              showFieldError(element, 'Please enter a valid email address');
              return false;
          }
          break;
          
      case 'phone':
          if (!isValidPhone(value)) {
              showFieldError(element, 'Please enter a valid phone number');
              return false;
          }
          break;
          
      case 'date':
          if (!isValidDate(value)) {
              showFieldError(element, 'Please enter a valid date (YYYY-MM-DD)');
              return false;
          }
          break;
          
      case 'text':
          if (rules.minLength && !isValidLength(value, rules.minLength)) {
              showFieldError(element, `Minimum ${rules.minLength} characters required`);
              return false;
          }
          if (rules.maxLength && !isValidLength(value, 0, rules.maxLength)) {
              showFieldError(element, `Maximum ${rules.maxLength} characters allowed`);
              return false;
          }
          break;
  }
  
  clearFieldError(element);
  return true;
}

/**
* Real-time Validation on Input
* @param {HTMLElement} element - Form element
* @param {Object} rules - Validation rules
*/
function enableRealTimeValidation(element, rules) {
  element.addEventListener('blur', () => validateElement(element, rules));
  element.addEventListener('input', () => {
      if (element.classList.contains('form-input--error')) {
          validateElement(element, rules);
      }
  });
}

/**
* Validate Entire Form
* @param {string|HTMLElement} form - Form ID or element
* @param {Object} rules - Validation rules object
* @returns {boolean} - True if all fields valid
*/
function validateForm(form, rules = {}) {
  if (typeof form === 'string') {
      form = document.getElementById(form);
  }
  
  if (!form) return false;
  
  let isValid = true;
  const inputs = form.querySelectorAll('input, textarea, select');
  
  inputs.forEach(input => {
      const fieldRules = rules[input.name] || {};
      if (fieldRules && Object.keys(fieldRules).length > 0) {
          if (!validateElement(input, fieldRules)) {
              isValid = false;
          }
      }
  });
  
  return isValid;
}
/**
 * CSRF Token Management
 * Automatically adds CSRF token to all AJAX requests
 */

// Get CSRF token from meta tag or cookie
function getCsrfToken() {
  // Try to get from meta tag
  let token = document.querySelector('meta[name="_csrf"]');
  if (token) {
      return token.getAttribute('content');
  }
  
  // Try to get from cookie
  const name = '_csrf=';
  const decodedCookie = decodeURIComponent(document.cookie);
  const cookieArray = decodedCookie.split(';');
  
  for (let i = 0; i < cookieArray.length; i++) {
      let cookie = cookieArray[i].trim();
      if (cookie.indexOf(name) === 0) {
          return cookie.substring(name.length);
      }
  }
  
  return '';
}

// Get CSRF header name
function getCsrfHeaderName() {
  let headerName = document.querySelector('meta[name="_csrf_header"]');
  if (headerName) {
      return headerName.getAttribute('content');
  }
  return 'X-CSRF-TOKEN';
}

// Configure Axios to include CSRF token
axios.interceptors.request.use(function(config) {
  const token = getCsrfToken();
  const headerName = getCsrfHeaderName();
  
  if (token) {
      config.headers[headerName] = token;
  }
  
  return config;
}, function(error) {
  return Promise.reject(error);
});
/**
 * Toast Notification System
 * Displays non-intrusive notifications to users
 */

/**
 * Show Success Toast
 * @param {string} title - Notification title
 * @param {string} message - Notification message
 * @param {number} duration - Duration in ms (default: 3000)
 */
function showSuccess(title, message, duration = 3000) {
  showToast('success', title, message, duration);
}

/**
* Show Error Toast
* @param {string} title - Error title
* @param {string} message - Error message
* @param {number} duration - Duration in ms (default: 5000)
*/
function showError(title, message, duration = 5000) {
  showToast('error', title, message, duration);
}

/**
* Show Warning Toast
* @param {string} title - Warning title
* @param {string} message - Warning message
* @param {number} duration - Duration in ms (default: 4000)
*/
function showWarning(title, message, duration = 4000) {
  showToast('warning', title, message, duration);
}

/**
* Show Info Toast
* @param {string} title - Info title
* @param {string} message - Info message
* @param {number} duration - Duration in ms (default: 3000)
*/
function showInfo(title, message, duration = 3000) {
  showToast('info', title, message, duration);
}

/**
* Generic Toast Display Function
* @param {string} type - Type: success, error, warning, info
* @param {string} title - Toast title
* @param {string} message - Toast message
* @param {number} duration - Duration in ms
*/
function showToast(type, title, message, duration = 3000) {
  const container = document.getElementById('toast-container');
  if (!container) return;
  
  // Define toast styles
  const typeConfig = {
      success: {
          icon: 'check-circle',
          bgColor: 'bg-green-50',
          borderColor: 'border-l-green-500',
          iconColor: 'text-green-600'
      },
      error: {
          icon: 'x-circle',
          bgColor: 'bg-red-50',
          borderColor: 'border-l-red-500',
          iconColor: 'text-red-600'
      },
      warning: {
          icon: 'alert-circle',
          bgColor: 'bg-yellow-50',
          borderColor: 'border-l-yellow-500',
          iconColor: 'text-yellow-600'
      },
      info: {
          icon: 'info',
          bgColor: 'bg-blue-50',
          borderColor: 'border-l-blue-500',
          iconColor: 'text-blue-600'
      }
  };
  
  const config = typeConfig[type] || typeConfig.info;
  
  // Create toast element
  const toast = document.createElement('div');
  toast.className = `${config.bgColor} ${config.borderColor} border-l-4 rounded-lg p-4 shadow-lg animate-slide-in flex items-start gap-3`;
  
  toast.innerHTML = `
      <i data-lucide="${config.icon}" class="w-5 h-5 ${config.iconColor} flex-shrink-0 mt-0.5"></i>
      <div class="flex-1">
          <h3 class="font-semibold text-gray-900">${escapeHtml(title)}</h3>
          <p class="text-sm text-gray-600 mt-1">${escapeHtml(message)}</p>
      </div>
      <button onclick="this.parentElement.remove()" class="text-gray-400 hover:text-gray-600">
          <i data-lucide="x" class="w-5 h-5"></i>
      </button>
  `;
  
  container.appendChild(toast);
  
  // Reinitialize Lucide icons for new elements
  if (typeof lucide !== 'undefined') {
      lucide.createIcons();
  }
  
  // Auto-remove after duration
  if (duration > 0) {
      setTimeout(() => {
          toast.style.animation = 'fadeOut 0.3s ease-out';
          setTimeout(() => toast.remove(), 300);
      }, duration);
  }
}

/**
* Escape HTML to prevent XSS
*/
function escapeHtml(text) {
  const map = {
      '&': '&amp;',
      '<': '&lt;',
      '>': '&gt;',
      '"': '&quot;',
      "'": '&#039;'
  };
  return text.replace(/[&<>"']/g, (char) => map[char]);
}

// Add fadeOut animation
const style = document.createElement('style');
style.textContent = `
  @keyframes fadeOut {
      from {
          opacity: 1;
          transform: translateX(0);
      }
      to {
          opacity: 0;
          transform: translateX(20px);
      }
  }
`;
document.head.appendChild(style);
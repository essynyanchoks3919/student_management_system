/**
 * DataTables Initialization & Utilities
 * Enhanced table functionality with sorting, searching, pagination
 */

/**
 * Initialize DataTable
 * @param {string} tableId - Table element ID
 * @param {Object} options - DataTable options
 */
function initDataTable(tableId, options = {}) {
  const defaultOptions = {
      pageLength: 10,
      lengthChange: true,
      searching: true,
      ordering: true,
      info: true,
      paging: true,
      responsive: true,
      language: {
          emptyTable: 'No data available in table',
          info: 'Showing _START_ to _END_ of _TOTAL_ entries',
          infoEmpty: 'Showing 0 to 0 of 0 entries',
          infoFiltered: '(filtered from _MAX_ total entries)',
          lengthMenu: 'Show _MENU_ entries',
          loadingRecords: 'Loading...',
          processing: 'Processing...',
          search: 'Search:',
          zeroRecords: 'No matching records found'
      }
  };
  
  const mergedOptions = { ...defaultOptions, ...options };
  
  if ($.fn.dataTable.isDataTable(`#${tableId}`)) {
      return $(`#${tableId}`).DataTable();
  }
  
  return $(`#${tableId}`).DataTable(mergedOptions);
}

/**
* Filter Table by Column
* @param {HTMLElement} input - Search input element
* @param {number} columnIndex - Column index to search
*/
function filterTableByColumn(input, columnIndex = 0) {
  const table = $('table').DataTable();
  table.column(columnIndex).search(input.value).draw();
}

/**
* Filter All Columns
* @param {HTMLElement} input - Search input element
*/
function filterTable(input) {
  const table = $('table').DataTable();
  if (table) {
      table.search(input.value).draw();
  }
}

/**
* Export Table to CSV
* @param {string} tableId - Table element ID
* @param {string} filename - Output filename
*/
function exportTableToCSV(tableId = 'studentsTable', filename = 'data.csv') {
  const table = document.getElementById(tableId);
  if (!table) return;
  
  let csv = [];
  let rows = table.querySelectorAll('tr');
  
  rows.forEach(row => {
      let csvRow = [];
      row.querySelectorAll('td, th').forEach((cell, index) => {
          // Skip checkboxes and action columns
          if (cell.querySelector('input[type="checkbox"]')) return;
          if (index === row.children.length - 1) return; // Skip last (action) column
          
          csvRow.push('"' + cell.textContent.trim().replace(/"/g, '""') + '"');
      });
      
      if (csvRow.length > 0) {
          csv.push(csvRow.join(','));
      }
  });
  
  downloadCSV(csv.join('\n'), filename);
}

/**
* Download CSV File
* @param {string} csvContent - CSV content
* @param {string} filename - Output filename
*/
function downloadCSV(csvContent, filename) {
  const link = document.createElement('a');
  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
  link.href = URL.createObjectURL(blob);
  link.download = filename;
  link.click();
}

/**
* Export Table to Excel (requires additional library)
* @param {string} tableId - Table element ID
* @param {string} filename - Output filename
*/
function exportTableToExcel(tableId, filename = 'data.xlsx') {
  const table = document.getElementById(tableId);
  if (!table) return;
  
  // This requires additional library like SheetJS
  // Fallback to CSV
  console.warn('Excel export requires SheetJS library. Using CSV instead.');
  exportTableToCSV(tableId, filename.replace('.xlsx', '.csv'));
}

/**
* Print Table
* @param {string} tableId - Table element ID
* @param {string} title - Print title
*/
function printTable(tableId, title = 'Report') {
  const table = document.getElementById(tableId);
  if (!table) return;
  
  const printWindow = window.open('', '', 'height=500,width=800');
  printWindow.document.write('<html><head><title>' + title + '</title>');
  printWindow.document.write('<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@3.4.1/tailwind.min.css">');
  printWindow.document.write('</head><body>');
  printWindow.document.write('<h1 class="text-2xl font-bold mb-4">' + title + '</h1>');
  printWindow.document.write(table.outerHTML);
  printWindow.document.write('</body></html>');
  printWindow.document.close();
  printWindow.print();
}

/**
* Add Row to Table
* @param {string} tableId - Table element ID
* @param {Array} rowData - Array of cell data
*/
function addTableRow(tableId, rowData) {
  const table = document.getElementById(tableId);
  if (!table) return;
  
  const tbody = table.querySelector('tbody');
  if (!tbody) return;
  
  const row = document.createElement('tr');
  row.className = 'border-b border-gray-100 hover:bg-gray-50';
  
  rowData.forEach(data => {
      const cell = document.createElement('td');
      cell.className = 'px-6 py-4';
      cell.textContent = data;
      row.appendChild(cell);
  });
  
  tbody.appendChild(row);
}

/**
* Clear Table Data
* @param {string} tableId - Table element ID
*/
function clearTable(tableId) {
  const table = document.getElementById(tableId);
  if (!table) return;
  
  const tbody = table.querySelector('tbody');
  if (tbody) {
      tbody.innerHTML = '';
  }
}

/**
* Get Selected Rows
* @param {string} selectorClass - Checkbox class name
* @returns {Array} - Array of selected row IDs
*/
function getSelectedRows(selectorClass = 'student-checkbox') {
  const checkboxes = document.querySelectorAll(`.${selectorClass}:checked`);
  return Array.from(checkboxes).map(cb => cb.value);
}

/**
* Delete Selected Rows
* @param {string} selectorClass - Checkbox class name
* @param {string} apiEndpoint - API endpoint to delete
*/
function deleteSelectedRows(selectorClass, apiEndpoint) {
  const selectedIds = getSelectedRows(selectorClass);
  
  if (selectedIds.length === 0) {
      showWarning('Warning', 'Please select at least one row');
      return;
  }
  
  showConfirmation(
      'Delete Selected?',
      `Are you sure you want to delete ${selectedIds.length} record(s)?`,
      () => {
          axios.post(`${apiEndpoint}/batch-delete`, { ids: selectedIds })
              .then(response => {
                  showSuccess('Success', 'Records deleted successfully');
                  location.reload();
              })
              .catch(error => {
                  showError('Error', error.response?.data?.message || 'Failed to delete records');
              });
      }
  );
}
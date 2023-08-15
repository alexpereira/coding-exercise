/**
 * Parse CSV file content to array
 * @param {*} fileContent CSV file content
 * @returns Array of transactions, e.g. [{ Date: 'MDY', Amount: 123.45 }]
 */
export const csvFileToTransactions = (fileContent) => {
  // clean file -- remove carriage returns
  fileContent = fileContent.replace(/[\r]+/g, '');

  const columns = fileContent.slice(0, fileContent.indexOf('\n')).split(',');
  const rows = fileContent.slice(fileContent.indexOf('\n') + 1).split('\n');

  const array = rows.map((rowIndex) => {
    const values = rowIndex.split(',');
    const obj = columns.reduce((object, header, index) => {
      const value = values[index];
      object[header] = header === 'Amount' ? parseFloat(value) : value;
      return object;
    }, {});
    return obj;
  });

  return array;
};

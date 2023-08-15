const MONTH_NAMES = ['January', 'February', 'March', 'April', 'May', 'June','July', 'August', 'September', 'October', 'November', 'December'];

/**
 * Get human readable month name from month number
 * @param {*} monthNumber Month number, where 0 is January
 * @returns Readable month name
 */
export const getMonthName = (monthNumber) => {
  return MONTH_NAMES[monthNumber];
}

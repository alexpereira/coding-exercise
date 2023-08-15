/**
 * Sleep for given amount of milliseconds
 * @param {*} ms Milliseconds
 * @returns 
 */
export const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));
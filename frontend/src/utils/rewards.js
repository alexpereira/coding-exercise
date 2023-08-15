const WEIGHT_1_MIN_AMOUNT = 50.00;
const WEIGHT_2_MIN_AMOUNT = 100.00;

/**
 * Calculate monthly rewards from list of transactions
 * @param {*} transactions Trasactions with date and amount, e.g. [{ date: 'MDY', amount: 0.99 }]
 * @returns MonthyRewards, e.g. [ { month: 1, totalTransactions: 123, totalAmount: 456.42, totalRewards: 352 } ]
 */
export const calculateMonthlyRewards = (transactions) => {
  const monthlyTransactions = toMonthlyTransactions(transactions);
  const monthlyRewards = [];

  for (const month in monthlyTransactions) {
    const transactions = monthlyTransactions[month];
    const totalTransactions = transactions.length;
    
    let totalAmount = 0;
    let totalRewards = 0;

    for (let index = 0; index < transactions.length; index++) {
      const transaction = transactions[index];
      const amount = transaction['Amount'];
      totalAmount += amount;

      let rewardsAmount = amount;
      let rewards = 0;
      
      if (rewardsAmount => WEIGHT_2_MIN_AMOUNT) {
        const rewards2Amount = rewardsAmount - WEIGHT_2_MIN_AMOUNT;
        rewards += Math.floor(rewards2Amount * 2);
        rewardsAmount -= rewards2Amount;
      }

      if (rewardsAmount => WEIGHT_1_MIN_AMOUNT) {
        const rewards1Amount = rewardsAmount - WEIGHT_1_MIN_AMOUNT;
        rewards += Math.floor(rewards1Amount * 1);
      }

      totalRewards += rewards;
    }

    monthlyRewards.push({ 
      month, 
      totalTransactions, 
      totalAmount: Math.round(totalAmount * 100) / 100, 
      totalRewards 
    });
  }

  return monthlyRewards;
};

/**
 * Group transactions by month
 * @param {*} transactions Trasactions with date and amount, e.g. [{ date: 'MDY', amount: 0.99 }]
 * @returns Transactions by month, e,g, { 1: [transactions], 2: [transactions] }
 */
const toMonthlyTransactions = (transactions) => {
  const monthlyTransactions = {};

  for (let index = 0; index < transactions.length; index++) {
    const transaction = transactions[index];
    const date = new Date(transaction['Date']);
    const month = date.getMonth();

    if (monthlyTransactions[month]) {
      monthlyTransactions[month].push(transaction);
    } else {
      monthlyTransactions[month] = [transaction];
    }
  }

  return monthlyTransactions;
}
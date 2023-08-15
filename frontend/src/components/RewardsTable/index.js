import React from 'react';

import { getMonthName } from '../../utils/date';
import './RewardsTable.css';

function RewardsTable({ rewards }) {
  return (
    <div className='RewardsTable'>
      <table>
        <tr>
          <th>Month</th>
          <th>Total Transactions</th>
          <th>Total Amount</th>
          <th>Total Rewards</th>
        </tr>
          {rewards.map((reward, index) => {
            return (
              <tr key={index}>
                <td>{getMonthName(reward.month)}</td>
                <td>{reward.totalTransactions}</td>
                <td>{reward.totalAmount}</td>
                <td>{reward.totalRewards}</td>
              </tr>
            )
          })}
      </table>
    </div>
  );
}

export default RewardsTable;

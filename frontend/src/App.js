import { useEffect, useState } from 'react';

import CSVInput from './components/CSVInput';
import RewardsTable from './components/RewardsTable';

import { csvFileToTransactions } from './utils/csv';
import { calculateMonthlyRewards } from './utils/rewards';
import { sleep } from './utils/misc';

import './App.css';

function App() {
  const [fakeLoading, setFakeLoading] = useState(false);
  const [fileContent, setFileContent] = useState();
  const [transactions, setTransactions] = useState();
  const [rewards, setRewards] = useState();

  const onFileSubmit = (fileContent) => { setFileContent(fileContent); };

  useEffect(() => {
    const mockAPICall = async () => {
      setFakeLoading(true);
      await sleep(1500);
      setFakeLoading(false);

      const transactions = csvFileToTransactions(fileContent);
      setTransactions(transactions);
    }
    if (fileContent) {
      mockAPICall();
    }
  }, [fileContent]);

  useEffect(() => {
    if (transactions) {
      const rewards = calculateMonthlyRewards(transactions);
      setRewards(rewards);
    }
  }, [transactions]);

  return (
    <div className='App'>
      <CSVInput onSubmit={onFileSubmit} />
      {fakeLoading && <p>Loading...</p>}
      {!fakeLoading && rewards !== undefined && <RewardsTable rewards={rewards} />}
    </div>
  );
}

export default App;

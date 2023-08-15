import React, { useState } from 'react';

import './CSVInput.css';

function CSVInput({ onSubmit }) {
  const [file, setFile] = useState();
  
  const fileReader = new FileReader();
  fileReader.onload = function (event) {
    const fileContent = event.target.result;
    onSubmit(fileContent);
  };

  const handleOnChange = (e) => {
    const file = e.target.files[0]
    if (file) {
      setFile(file);
      fileReader.readAsText(file);
    }
  };

  return (
    <div className='CSVInput'>
      <h3> {(file && file.name) || 'Click the box to upload'}</h3>

      <input
        type={'file'}
        accept={'.csv'}
        onChange={handleOnChange}
      />
    </div>
  );
}

export default CSVInput;

import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Click box to upload input', () => {
  render(<App />);
  const inputElement = screen.getByText(/Click the box to upload/i);
  expect(inputElement).toBeInTheDocument();
});

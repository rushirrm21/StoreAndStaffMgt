import { render, screen } from '@testing-library/react';
import Login from './Login';

test('renders Log In page', () => {
    render(<Login />);
    const linkElement = screen.getByText(/Login/);
    expect(linkElement).toBeInTheDocument();
  });
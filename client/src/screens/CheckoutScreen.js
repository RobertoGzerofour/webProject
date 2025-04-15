import React, { useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';
import { useNavigate } from 'react-router-dom';

const CheckoutScreen = () => {
  const [address, setAddress] = useState({
    street: '',
    city: '',
    state: '',
    postalCode: '',
    country: '',
  });
  const [cartItems, setCartItems] = useState([]);
  const [totalCost, setTotalCost] = useState(0);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const storedCartItems = JSON.parse(localStorage.getItem('cart')) || [];
    const storedTotalCost = parseFloat(localStorage.getItem('total')) || 0;

    setCartItems(storedCartItems);
    setTotalCost(storedTotalCost);
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setAddress((prevAddress) => ({ ...prevAddress, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const products = cartItems.map((item) => ({
      id: item._id,
      name: item.name,
      price: item.price,
      artist: item.artist,
      imageLink: item.imageLink,
    }));

    try {
      const token = localStorage.getItem('authToken');
      if (!token) {
        setErrorMessage('You must be logged in to place the order.');
        return;
      }

      const response = await fetch('http://localhost:8080/api/orders', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          address,
          product: products,
          totalCost,
        }),
      });

      let data = {};
      try {
        data = await response.json();
      } catch (error) {
        console.warn('No JSON response body:', error);
        console.log("Token being sent:", token);
        console.log(data);
      }

      if (!response.ok) {
        setErrorMessage(data.message || 'Failed to place the order.');
        return;
      }

      console.log('Order created:', data);

      // ✅ Optional improvement: clear cart after successful order
      localStorage.removeItem('cart');
      localStorage.removeItem('total');

      // ✅ Navigate to order confirmation page using correct 'id'
      navigate(`/order/${data.id}`);
      console.log("Order created:", data);
    } catch (error) {
      console.error('Checkout Error:', error);
      setErrorMessage('Failed to place the order.');
    }
  };

  return (
    <div
      style={{
        backgroundColor: '#121212',
        color: '#FFFFFF',
        fontFamily: 'Circular, Helvetica, Arial, sans-serif',
        padding: '20px',
        maxWidth: '600px',
        margin: '0 auto',
      }}
    >
      <h1 style={{ fontSize: '24px', marginBottom: '20px' }}>Checkout</h1>

      {errorMessage && (
        <div style={{ color: 'red', marginBottom: '20px' }}>{errorMessage}</div>
      )}

      <form onSubmit={handleSubmit}>
        <h2 style={{ fontSize: '20px', marginBottom: '10px' }}>Address</h2>
        {['street', 'city', 'state', 'postalCode', 'country'].map((field) => (
          <div key={field} style={{ marginBottom: '15px' }}>
            <label>{field.charAt(0).toUpperCase() + field.slice(1)}:</label>
            <input
              type="text"
              name={field}
              value={address[field]}
              onChange={handleInputChange}
              required
              style={{
                backgroundColor: '#282828',
                border: 'none',
                color: '#FFF',
                padding: '10px',
                width: '100%',
                borderRadius: '5px',
                marginTop: '5px',
              }}
            />
          </div>
        ))}
        <button
          type="submit"
          style={{
            backgroundColor: '#1DB954',
            border: 'none',
            color: '#FFF',
            padding: '10px 20px',
            borderRadius: '50px',
            cursor: 'pointer',
            fontWeight: 'bold',
          }}
        >
          Place Order
        </button>
      </form>

      <h2 style={{ fontSize: '20px', margin: '20px 0 10px' }}>Cart Items</h2>
      {cartItems.length > 0 ? (
        <ul>
          {cartItems.map((item, index) => (
            <li key={index} style={{ marginBottom: '10px' }}>
              {item.name} - ${item.price} x {item.quantity}
            </li>
          ))}
        </ul>
      ) : (
        <p>Your cart is empty.</p>
      )}

      <h3 style={{ fontSize: '18px', marginTop: '20px' }}>
        Total Cost: ${totalCost.toFixed(2)}
      </h3>
    </div>
  );
};

export default CheckoutScreen;

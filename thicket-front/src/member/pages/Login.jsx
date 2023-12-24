import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {H1} from "../../assets/css/setting/admin/StylesOfCreate";


const inlineStyles = {
  loginFormContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    textAlign: 'center',
    height: '100vh',
  },
  formRow: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: '10px',
  },
  label: {
    width: '120px',
  },
  input: {
    padding: '8px',
    border: '1px solid #ccc',
    borderRadius: '4px',
    outline: 'none',
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: '10px',
  },
  button: {
    padding: '8px 16px',
    backgroundColor: '#007bff',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    marginRight: '10px',
  },
  signupButton: {
    padding: '8px 16px',
    backgroundColor: '#ffcc00',
    color: '#fff',
    borderRadius: '4px',
  },
};

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();


  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: JSON.stringify({
        "email": email,
        "password": password
      }),
      redirect: 'follow'
    };

    fetch("/members/USER", requestOptions)
        .then(response => {
          localStorage.setItem('token', response.headers.get('Authorization'));
          console.log(localStorage.getItem('token'));
          return response;
        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    navigate("/")
  };

  const handleSignUp = () => {
    navigate('/auth');
  };

  return (
      <div style={inlineStyles.loginFormContainer}>
        <div style={{ border: '1px solid #000', borderRadius: '5px', padding: '10px', display: 'inline-block' }}>
          <form onSubmit={handleSubmit}>
            <H1>로그인</H1>
            <div style={inlineStyles.formRow}>
              <label style={inlineStyles.label}>이메일 주소:</label>
              <input type="email" value={email} onChange={handleEmailChange} style={inlineStyles.input} />
            </div>
            <div style={inlineStyles.formRow}>
              <label style={inlineStyles.label}>비밀번호:</label>
              <input type="password" value={password} onChange={handlePasswordChange} style={inlineStyles.input} />
            </div>
            <div style={inlineStyles.buttonContainer}>
              <button type="submit" style={inlineStyles.button}>로그인</button>
              <button className="signup-button" onClick={handleSignUp} style={inlineStyles.signupButton}>회원가입</button>
            </div>
          </form>
        </div>
      </div>
  );
}

export default Login;
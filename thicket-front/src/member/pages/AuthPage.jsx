import React, { useContext, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {EmailContext} from "../component/Layout";
import {H1} from "../../assets/css/setting/admin/StylesOfCreate";

const inlineStyles = {
  authPageContainer: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
  },
  formRow: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: '20px',
  },
  button: {
    marginLeft: '10px',
  },
  modal: {
    position: 'fixed',
    top: '0',
    left: '0',
    width: '100%',
    height: '100%',
    background: 'rgba(0, 0, 0, 0.5)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalContent: {
    backgroundColor: '#fff',
    padding: '20px',
    borderRadius: '5px',
    textAlign: 'center',
  },
};

function AuthPage() {
  const { email, setEmail } = useContext(EmailContext);
  const [modalOpen, setModalOpen] = useState(false);
  const [authCode, setAuthCode] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get(`http://localhost:8000/email?email=${email}`, { email });
      console.log(response.data);
      openModal();
    } catch (error) {
      console.error(error);
    }
  };

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const sendAuthCode = async () => {
    try {
      const response = await axios.post('http://localhost:8000/email', { email, code: authCode });
      console.log(response.data);

      if (response.status === 200) {
        closeModal();
        navigate('/signup');
      } else {
        // Handle other status codes or show an error message
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
      <div style={inlineStyles.authPageContainer}>
        <div style={{ border: '1px solid #000', borderRadius: '5px', padding: '10px', display: 'inline-block' }}>
          <H1>인증 부탁드립니다</H1>
          <form onSubmit={handleSubmit}>
            <div style={inlineStyles.formRow}>
              <label>이메일 주소_</label>
              <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
              <button type="submit" style={inlineStyles.button}>제출</button>
            </div>
          </form>

          {modalOpen && (
              <div style={inlineStyles.modal}>
                <div style={inlineStyles.modalContent}>
                  <h1>인증 번호를 입력하세요</h1>
                  <input type="text" value={authCode} onChange={(e) => setAuthCode(e.target.value)} />
                  <button onClick={sendAuthCode} style={inlineStyles.button}>제출</button>
                  <button onClick={closeModal} style={inlineStyles.button}>닫기</button>
                </div>
              </div>
          )}
        </div>
      </div>
  );
}

export default AuthPage;
import React, {useState, useRef, useEffect} from 'react';
import DatePicker, {registerLocale} from 'react-datepicker';
import ko from 'date-fns/locale/ko';
import 'react-datepicker/dist/react-datepicker.css';
import ReactDOM from 'react-dom';
import styled from "styled-components";

const createContainerStyle = {
    padding: '10px',
    overflowY: 'auto', // Add overflow property for vertical scrollbar
    maxHeight: '80vh', // Set a max height to trigger scrollbar
};

const customDivStyle = {
    padding: '20px 0px 60px 0',
};

const infoTableStyle = {
    width: '100%',
    borderCollapse: 'collapse',
};

const customH1Style = {
    margin: '0 0 20px 0',
    paddingLeft: '5px',
};

const customThStyle = {
    textAlign: 'left',
    height: '29px',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: '#f7f7f7',
    minWidth: '180px', // Use minWidth instead of width
    maxWidth: '180px', // Use maxWidth to limit the width
    whiteSpace: 'nowrap', // Prevent text from wrapping
    overflow: 'hidden', // Hide overflow text
    textOverflow: 'ellipsis', // Display ellipsis for overflow text
    borderTop: '1px solid #000',
};

const customTdStyle = {
    textAlign: 'left',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: 'white',
    borderTop: '1px solid #000',
};

const customInputStyle = {
    width: '300px',
    height: '25px',
    boxSizing: 'border-box',
};

const customTextareaStyle = {
    width: '300px',
    height: '25px',
    boxSizing: 'border-box',
    padding: '5px 0px 0px 1px',
};

const customInputTimeStyle = {
    width: '50px',
    height: '25px',
    boxSizing: 'border-box',
    marginBottom: '10px',
};

const customButtonStyle = {
    marginLeft: '10px',
    cursor: 'pointer',
    padding: '4px 8px',
    border: '1px solid #000',
    borderRadius: '5px',
};

const customButton_1Style = {
    cursor: 'pointer',
    padding: '4px 8px',
    border: '1px solid #000',
    borderRadius: '5px',
};

const xButtonStyle = {
    position: 'absolute',
    right: '10px',
    padding: '0px 3px',
};

const StyledDatePicker = styled(DatePicker)`
  width: 300px;
  height: 25px;
  box-sizing: border-box;
`;

const AdminEditShow = () => {

    const [startDate, setStartDate] = useState(null);   // 전체 시작일
    const [endDate, setEndDate] = useState(null);       // 전체 종료일
    const startDatePickerRef = useRef(null);    // 전체 시작일 달력
    const endDatePickerRef = useRef(null);      // 전체 종료일 달력
    const [hasExistingSchedules, setHasExistingSchedules] = useState(false); // 달력 비활성화
    const [timeSlots, setTimeSlots] = useState([]);        // 일별 시작 시간
    const [selectedPerformanceType, setSelectedPerformanceType] = useState('');     // 공연 종류
    const [selectedPerformanceStatus, setSelectedPerformanceStatus] = useState(''); // 공연 상태
    const [uploadedFiles, setUploadedFiles] = useState([]);    // 공연포스터 이미지
    const fileInputRef = useRef(null);          // 공연포스터 이미지
    const [selectedImage, setSelectedImage] = useState(null);         // 공연포스터 이미지
    const [uploadedDetailImages, setUploadedDetailImages] = useState([]); // 상세페이지 이미지
    const detailImageInputRef = useRef(null);              // 상세페이지 이미지
    const [selectedDetailImage, setSelectedDetailImage] = useState(null);        // 상세페이지 이미지
    const [seatValues, setSeatValues] = useState([]);   // 좌석

    // 달력 한글화
    registerLocale('ko', ko);

    // 달력 부분 비활성화
    useEffect(() => {
        if (timeSlots.length > 0) {
            setHasExistingSchedules(true);
        } else {
            setHasExistingSchedules(false);
        }
    }, [timeSlots]);

    const ImageViewerModal = ({ imageUrl, onClose }) => {
        return (
            <div
                style={{
                    position: 'fixed',
                    top: 0,
                    left: 0,
                    width: '100%',
                    height: '100%',
                    backgroundColor: 'rgba(0, 0, 0, 0.5)',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
                onClick={onClose}
            >
                <img
                    src={imageUrl}
                    alt="Enlarged"
                    style={{ maxWidth: '95%', maxHeight: '95%', objectFit: 'contain' }}
                />
            </div>
        );
    };

    // 공연포스터 이미지 업로드
    const handleFileUpload = (e) => {
        const file = e.target.files[0];

        // Check if the limit (1 file) has been reached
        if (uploadedFiles.length >= 1) {
            alert('공연포스터 이미지는 1개만 등록 가능합니다. 삭제 후 등록해 주세요.');
            return;
        }

        setUploadedFiles((prevFiles) => [...prevFiles, file]);
    };

    // 공연포스터 이미지 삭제
    const handleRemoveFile = (index) => {
        setUploadedFiles((prevFiles) => {
            const newFiles = [...prevFiles];
            newFiles.splice(index, 1);
            return newFiles;
        });
    };

    // 공연포스터 이미지 클릭 시 모달 열기
    const handleImageClick = (imageUrl) => {
        setSelectedImage(imageUrl);
    };

    // 공연 포스터 모달 닫기
    const handleCloseModal = () => {
        setSelectedImage(null);
    };

    // 상세페이지 이미지 파일 업로드
    const handleDetailImageUpload = (e) => {
        // 상세페이지 이미지 업로드 로직 추가
        const file = e.target.files[0];
        // 상세페이지 이미지 파일을 uploadedDetailImages 상태에 추가
        setUploadedDetailImages((prevImages) => [...prevImages, file]);
    };

    // 상세페이지 이미지 삭제
    const handleRemoveDetailImage = (index) => {
        // 상세페이지 이미지 삭제 로직 추가
        setUploadedDetailImages((prevImages) => {
            const newImages = [...prevImages];
            newImages.splice(index, 1);
            return newImages;
        });
    };

    // 상세페이지 이미지 클릭 시 모달 열기
    const handleDetailImageClick = (imageUrl) => {
        setSelectedDetailImage(imageUrl);
    };

    // 상세페이지 이미지 모달 닫기
    const handleCloseDetailImageModal = () => {
        setSelectedDetailImage(null);
    };

    return (
        <div style={createContainerStyle} >
            <div>
                <h1 style={customH1Style} >
                    공연 수정
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>제목</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  제목을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연장 주소</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  주소를 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>전체 시작일</th>
                        <td style={customTdStyle}>
                            <div style={{ position: 'relative', display: 'flex', alignItems: 'center' }}>
                                <StyledDatePicker
                                    ref={startDatePickerRef}
                                    selected={startDate}
                                    onChange={(date) => setStartDate(date)}
                                    dateFormat="yyyy년 MM월 dd일"
                                    placeholderText="  날짜를 선택하세요."
                                    locale="ko"
                                    maxDate={endDate} // 종료일 이후로 선택 불가능
                                    disabled={true} // 비활성화
                                />
                                <div
                                    style={{
                                        position: 'absolute',
                                        left: '280px',
                                        top: '5px',
                                        cursor: 'pointer',
                                    }}
                                    onClick={() => startDatePickerRef.current && startDatePickerRef.current.setOpen(true)}
                                >
                                    {/* Calendar Icon */}
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 24 24"
                                        width="16"
                                        height="16"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    >
                                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                </div>
                                <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                                    수정할 수 없습니다.
                                </p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>전체 종료일</th>
                        <td style={customTdStyle}>
                            <div style={{ position: 'relative', display: 'flex', alignItems: 'center' }}>
                                <StyledDatePicker
                                    ref={endDatePickerRef}
                                    selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    dateFormat="yyyy년 MM월 dd일"
                                    placeholderText="  날짜를 선택하세요."
                                    locale="ko"
                                    minDate={startDate} // 시작일 이전으로 설정 불가능
                                    disabled={true} // 비활성화
                                />
                                <div
                                    style={{
                                        position: 'absolute',
                                        left: '280px', // Adjust the left position as needed
                                        top: '5px',
                                        cursor: 'pointer',
                                    }}
                                    onClick={() => endDatePickerRef.current && endDatePickerRef.current.setOpen(true)}
                                >
                                    {/* Calendar Icon */}
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 24 24"
                                        width="16"
                                        height="16"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    >
                                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                </div>
                                <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                                    수정할 수 없습니다.
                                </p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 시간</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  시간을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>관람 연령</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  연령제한을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 종류</th>
                        <td style={customTdStyle}>
                            <select
                                style={customInputStyle}
                                value={selectedPerformanceType}
                                onChange={(e) => setSelectedPerformanceType(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="뮤지컬">뮤지컬</option>
                                <option value="연극">연극</option>
                                <option value="콘서트">콘서트</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 상태</th>
                        <td style={customTdStyle}>
                            <select
                                style={customInputStyle}
                                value={selectedPerformanceStatus}
                                onChange={(e) => setSelectedPerformanceStatus(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="공연전">공연전</option>
                                <option value="공연중">공연중</option>
                                <option value="공연완료">공연완료</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연포스터 이미지</th>
                        <td style={customTdStyle}>
                            <input
                                type="file"
                                id="imageFile"
                                ref={fileInputRef}
                                style={{ display: 'none' }}
                                onChange={handleFileUpload}
                            />
                            <button
                                style={customButton_1Style}
                                onClick={() => fileInputRef.current.click()}
                            >
                                파일선택
                            </button>
                        </td>
                    </tr>
                    {uploadedFiles.map((file, index) => (
                        <tr key={index}>
                            <th style={customThStyle}>
                                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                    {`썸네일`}
                                    {/* Add a delete button next to each uploaded image */}
                                    <button
                                        style={{ padding: '0px 3px' }}
                                        onClick={() => handleRemoveFile(index)}
                                    >
                                        ✕
                                    </button>
                                </div>
                            </th>
                            <td style={customTdStyle}>
                                <div style={{ display: 'flex', alignItems: 'center' }}>
                                    <img
                                        src={URL.createObjectURL(file)}
                                        alt={`File ${index + 1}`}
                                        style={{ maxWidth: '100px', maxHeight: '100px', cursor: 'pointer' }}
                                        onClick={() => handleImageClick(URL.createObjectURL(file))}
                                    />
                                    <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                                        {file.name}
                                        <br />
                                        (클릭하여 이미지를 크게 보기)
                                    </p>
                                </div>
                            </td>
                        </tr>
                    ))}
                    {selectedImage && ReactDOM.createPortal(
                        <ImageViewerModal
                            imageUrl={selectedImage}
                            onClose={handleCloseModal}
                        />,
                        document.body
                    )}
                    <tr>
                        <th style={customThStyle}>상세페이지 이미지</th>
                        <td style={customTdStyle}>
                            <input
                                type="file"
                                id="detailImageFile"
                                ref={detailImageInputRef}
                                style={{ display: 'none' }}
                                onChange={handleDetailImageUpload}
                            />
                            <button
                                style={customButton_1Style}
                                onClick={() => detailImageInputRef.current.click()}
                            >
                                파일선택
                            </button>
                        </td>
                    </tr>
                    {uploadedDetailImages.map((image, index) => (
                        <tr key={index}>
                            <th style={customThStyle}>
                                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                    {`썸네일`}
                                    <button
                                        style={{ padding: '0px 3px' }}
                                        onClick={() => handleRemoveDetailImage(index)}
                                    >
                                        ✕
                                    </button>
                                </div>
                            </th>
                            <td style={customTdStyle}>
                                <div style={{ display: 'flex', alignItems: 'center' }}>
                                    <img
                                        src={URL.createObjectURL(image)}
                                        alt={`File ${index + 1}`}
                                        style={{ maxWidth: '100px', maxHeight: '100px', cursor: 'pointer' }}
                                        onClick={() => handleDetailImageClick(URL.createObjectURL(image))}
                                    />
                                    <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                                        {image.name}
                                        <br />
                                        (클릭하여 이미지를 크게 보기)
                                    </p>
                                </div>
                            </td>
                        </tr>
                    ))}
                    {selectedDetailImage && ReactDOM.createPortal(
                        <ImageViewerModal
                            imageUrl={selectedDetailImage}
                            onClose={handleCloseDetailImageModal}
                        />,
                        document.body
                    )}
                    <tr>
                        <th style={customThStyle}>상세페이지 텍스트 </th>
                        <td style={customTdStyle}>
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                <textarea style={customTextareaStyle} placeholder="  이미지 외 추가설명을 입력하세요." />
                                <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                                    텍스트 상자의 오른쪽 하단을 클릭해서 입력창을 조절할 수 있습니다.
                                </p>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div style={customDivStyle}>
                <button style={customButtonStyle}>공연 수정</button>
            </div>
            <div>
                <h1 style={customH1Style} >
                    일정
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>일별 시작 시간</th>
                        <td style={customTdStyle}>수정할 수 없습니다.</td>
                    </tr>
                    {timeSlots.map((dateTime, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={customThStyle}>
                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                        <div>
                                            {new Date(dateTime.date).toLocaleDateString('ko-KR', {
                                                year: 'numeric',
                                                month: '2-digit',
                                                day: '2-digit',
                                            })}
                                        </div>
                                    </div>
                                </th>
                                <td style={customTdStyle}>
                                    <div style={{ display: 'flex' }}>
                                        {dateTime.times.map((time, timeIndex) => (
                                            <div key={timeIndex} style={{ marginRight: '20px' }}>
                                                {`${time.hour}:${time.minute}`}
                                            </div>
                                        ))}
                                    </div>
                                </td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
            <div>
                <h1 style={{ margin: '60px 0 20px 0', paddingLeft: '5px' }}>
                    좌석
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>좌석</th>
                        <td style={customTdStyle}>수정할 수 없습니다.</td>
                    </tr>
                    {seatValues.map((value, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={{ ...customThStyle, position: 'relative' }}>{`타입 : ${value[0].value}석`}
                                </th>
                                <td style={customTdStyle}>
                                    <div>{`개수 : ${value[1].value}개`}</div>
                                    <div>{`가격 : ${value[2].value}원`}</div>
                                </td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AdminEditShow;
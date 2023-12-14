import React, {useState, useRef, useEffect} from 'react';
import {registerLocale} from 'react-datepicker';
import ko from 'date-fns/locale/ko';
import 'react-datepicker/dist/react-datepicker.css';
import ReactDOM from 'react-dom';
import {
    BetweenDiv, Button,
    Button1, ButtonX,
    CalendarSVG, CalenderDiv,
    Container, CustomDiv, CustomH1, FlexCenterDiv,
    H1, Img,
    Input, InputFile, P, RelativeDiv, Select,
    StyledDatePicker,
    Table,
    Td, Textarea,
    Th
} from "../../assets/css/setting/admin/Styles1";

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
        <Container>
            <div>
                <H1>공연 수정</H1>
                <Table>
                    <tbody>
                    <tr>
                        <Th>제목</Th>
                        <Td>
                            <Input placeholder="  제목을 입력하세요." />
                        </Td>
                    </tr>
                    <tr>
                        <Th>공연장 주소</Th>
                        <Td>
                            <Input placeholder="  주소를 입력하세요." />
                        </Td>
                    </tr>
                    <tr>
                        <Th>전체 시작일</Th>
                        <Td>
                            <RelativeDiv>
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
                                <CalenderDiv onClick={() => startDatePickerRef.current && startDatePickerRef.current.setOpen(true)}>
                                    <CalendarSVG />
                                </CalenderDiv>
                                <P>수정할 수 없습니다.</P>
                            </RelativeDiv>
                        </Td>
                    </tr>
                    <tr>
                        <Th>전체 종료일</Th>
                        <Td>
                            <RelativeDiv>
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
                                <CalenderDiv onClick={() => endDatePickerRef.current && endDatePickerRef.current.setOpen(true)}>
                                    <CalendarSVG />
                                </CalenderDiv>
                                <P>수정할 수 없습니다.</P>
                            </RelativeDiv>
                        </Td>
                    </tr>
                    <tr>
                        <Th>공연 시간</Th>
                        <Td>
                            <Input placeholder="  시간을 입력하세요." />
                        </Td>
                    </tr>
                    <tr>
                        <Th>관람 연령</Th>
                        <Td>
                            <Input placeholder="  연령제한을 입력하세요." />
                        </Td>
                    </tr>
                    <tr>
                        <Th>공연 종류</Th>
                        <Td>
                            <Select
                                value={selectedPerformanceType}
                                onChange={(e) => setSelectedPerformanceType(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="뮤지컬">뮤지컬</option>
                                <option value="연극">연극</option>
                                <option value="콘서트">콘서트</option>
                            </Select>
                        </Td>
                    </tr>
                    <tr>
                        <Th>공연 상태</Th>
                        <Td>
                            <Select
                                value={selectedPerformanceStatus}
                                onChange={(e) => setSelectedPerformanceStatus(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="공연전">공연전</option>
                                <option value="공연중">공연중</option>
                                <option value="공연완료">공연완료</option>
                            </Select>
                        </Td>
                    </tr>
                    <tr>
                        <Th>공연포스터 이미지</Th>
                        <Td>
                            <InputFile
                                type="file"
                                ref={fileInputRef}
                                onChange={handleFileUpload}
                            />
                            <Button1 onClick={() => fileInputRef.current.click()}>
                                파일선택
                            </Button1>
                        </Td>
                    </tr>
                    {uploadedFiles.map((file, index) => (
                        <tr key={index}>
                            <Th>
                                <BetweenDiv>
                                    썸네일
                                    <ButtonX onClick={() => handleRemoveFile(index)}>
                                        ✕
                                    </ButtonX>
                                </BetweenDiv>
                            </Th>
                            <Td>
                                <FlexCenterDiv>
                                    <Img
                                        src={URL.createObjectURL(file)}
                                        alt={`File ${index + 1}`}
                                        onClick={() => handleImageClick(URL.createObjectURL(file))}
                                    />
                                    <P>
                                        {file.name}
                                        <br />
                                        (클릭하여 이미지를 크게 보기)
                                    </P>
                                </FlexCenterDiv>
                            </Td>
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
                        <Th>상세페이지 이미지</Th>
                        <Td>
                            <InputFile
                                type="file"
                                ref={detailImageInputRef}
                                onChange={handleDetailImageUpload}
                            />
                            <Button1 onClick={() => detailImageInputRef.current.click()}>
                                파일선택
                            </Button1>
                        </Td>
                    </tr>
                    {uploadedDetailImages.map((file, index) => (
                        <tr key={index}>
                            <Th>
                                <BetweenDiv>
                                    썸네일
                                    <ButtonX onClick={() => handleRemoveDetailImage(index)}>
                                        ✕
                                    </ButtonX>
                                </BetweenDiv>
                            </Th>
                            <Td>
                                <FlexCenterDiv>
                                    <Img
                                        src={URL.createObjectURL(file)}
                                        alt={`File ${index + 1}`}
                                        onClick={() => handleDetailImageClick(URL.createObjectURL(file))}
                                    />
                                    <P>
                                        {file.name}
                                        <br />
                                        (클릭하여 이미지를 크게 보기)
                                    </P>
                                </FlexCenterDiv>
                            </Td>
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
                        <Th>상세페이지 텍스트</Th>
                        <Td>
                            <FlexCenterDiv>
                                <Textarea placeholder="  이미지 외 추가설명을 입력하세요." />
                                <P>텍스트 상자의 오른쪽 하단을 클릭해서 입력창을 조절할 수 있습니다.</P>
                            </FlexCenterDiv>
                        </Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
            <CustomDiv>
                <Button>공연 수정</Button>
            </CustomDiv>
            <div>
                <H1>일정</H1>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="1">일별 시작 시간</Th>
                        <Td>수정할 수 없습니다.</Td>
                    </tr>
                    {timeSlots.map((dateTime, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <Th>
                                    <BetweenDiv>
                                        <div>
                                            {new Date(dateTime.date).toLocaleDateString('ko-KR', {
                                                year: 'numeric',
                                                month: '2-digit',
                                                day: '2-digit',
                                            })}
                                        </div>
                                    </BetweenDiv>
                                </Th>
                                <Td>
                                    <FlexCenterDiv>
                                        {dateTime.times.map((time, timeIndex) => (
                                            <div key={timeIndex} style={{ marginRight: '20px' }}>
                                                {`${time.hour}:${time.minute}`}
                                            </div>
                                        ))}
                                    </FlexCenterDiv>
                                </Td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </Table>
            </div>
            <div>
                <CustomH1>좌석</CustomH1>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="1">좌석</Th>
                        <Td>수정할 수 없습니다.</Td>
                    </tr>
                    {seatValues.map((value, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <Th>
                                    <BetweenDiv>
                                        {`타입 : ${value[0].value}석`}
                                    </BetweenDiv>
                                </Th>
                                <Td>
                                    <div>{`개수 : ${value[1].value}개`}</div>
                                    <div>{`가격 : ${value[2].value}원`}</div>
                                </Td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};

export default AdminEditShow;
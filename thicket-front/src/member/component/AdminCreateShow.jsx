import React, {useState, useRef, useEffect} from 'react';
import {registerLocale} from 'react-datepicker';
import ko from 'date-fns/locale/ko';
import 'react-datepicker/dist/react-datepicker.css';
import ReactDOM from 'react-dom';
import {
    Container,
    CustomDiv, RelativeDiv, FlexCenterDiv, BetweenDiv, TimeDiv, WidthDiv, CalenderDiv,
    H1, H2, P,
    Input, InputFile, Textarea, Select,
    StyledDatePicker, Img,
    Button, Button1, Button2, ButtonX,
    Table, Th, Td,
    CalendarSVG, ImageViewerModal,
} from '../../assets/css/setting/admin/Styles1';
import SeatModalContent from "../../assets/css/setting/admin/SeatModalContent";

const AdminCreateShow = () => {

    const [startDate, setStartDate] = useState(null);   // 전체 시작일
    const [endDate, setEndDate] = useState(null);       // 전체 종료일
    const startDatePickerRef = useRef(null);    // 전체 시작일 달력
    const endDatePickerRef = useRef(null);       // 전체 종료일 달력
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

    // 일정 등록 날짜 조정
    const addDays = (date, days) => {
        const result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    };

    // 일별 시작 시간 등록
    const TimeSelection = ({ onConfirm }) => {
        const [selectedDate, setSelectedDate] = useState('');
        const [selectedHour, setSelectedHour] = useState('');
        const [selectedMinute, setSelectedMinute] = useState('');

        const handleConfirm = () => {
            if (selectedDate && selectedHour !== '' && selectedMinute !== '') {
                const formattedDateTime = {
                    date: selectedDate,
                    times: [{ hour: selectedHour, minute: selectedMinute }],
                };

                onConfirm(formattedDateTime);

                setTimeSlots((prevTimeSlots) => {
                    const updatedTimeSlots = [...prevTimeSlots];
                    const existingDateIndex = updatedTimeSlots.findIndex((item) => item.date === formattedDateTime.date);

                    if (existingDateIndex !== -1) {
                        updatedTimeSlots[existingDateIndex].times.sort((a, b) => {
                            const timeA = new Date(`1970-01-01T${a.hour}:${a.minute}`);
                            const timeB = new Date(`1970-01-01T${b.hour}:${b.minute}`);
                            return timeA - timeB;
                        });
                    }

                    return updatedTimeSlots.sort((a, b) => new Date(a.date) - new Date(b.date));
                });

                console.log(formattedDateTime);
            }
        };

        return (
            <div style={{ textAlign: 'center' }}>
                <div
                    style={{
                        border: '1px solid #000',
                        borderRadius: '5px',
                        padding: '10px',
                        display: 'inline-block',
                    }}
                >
                    <H2>날짜 및 시간 선택</H2>
                    <div>
                        <input
                            type="date"
                            style={{ width: '136px', marginBottom: '10px' }}
                            value={selectedDate}
                            onChange={(e) => setSelectedDate(e.target.value)}
                            min={startDate ? addDays(startDate, 1).toISOString().split('T')[0] : undefined}
                            max={endDate ? addDays(endDate, 1).toISOString().split('T')[0] : undefined}
                        />
                    </div>
                    <div>
                        <select
                            style={{
                                width: '50px',
                                height: '25px',
                                boxSizing: 'border-box',
                                marginBottom: '10px',
                            }}
                            value={selectedHour}
                            onChange={(e) => setSelectedHour(e.target.value === '24' ? '00' : e.target.value)}
                        >
                            <option value="">선택</option>
                            {Array.from({ length: 24 }, (_, i) => i.toString().padStart(2, '0')).map((hour) => (
                                <option key={hour} value={hour}>
                                    {hour === '24' ? '00' : hour}
                                </option>
                            ))}
                        </select>시
                        <select
                            style={{
                                width: '50px',
                                height: '25px',
                                boxSizing: 'border-box',
                                marginBottom: '10px',
                                marginLeft: '5px',
                            }}
                            value={selectedMinute}
                            onChange={(e) => setSelectedMinute(e.target.value)}
                        >
                            <option value="">선택</option>
                            <option value="00">00</option>
                            <option value="30">30</option>
                        </select>분
                    </div>
                    <button type="button" onClick={handleConfirm}>
                        등록
                    </button>
                </div>
            </div>
        );
    };

    // 일별 시작 시간 일정추가 버튼 (유효성 검사 포함)
    const handleAddTimeButtonClick = () => {
        if (!startDate || !endDate || startDate === 'Invalid Date' || endDate === 'Invalid Date') {
            alert('전체 시작일과 전체 종료일을 먼저 선택하세요.');
            return;
        }
        const newWindow = window.open('', '_blank', 'width=240,height=220,left=100,top=100');

        ReactDOM.render(
            <TimeSelection
                onConfirm={(selectedDateTime) => {
                    const { date, times } = selectedDateTime;

                    // 각 시간 항목의 유효성 검사
                    const isValidTime = times.every((time) => {
                        const hour = parseInt(time.hour, 10);
                        const minute = parseInt(time.minute, 10);

                        return (hour >= 0 && hour <= 23) && (minute === 0 || minute === 30);
                    });

                    if (!isValidTime) {
                        alert('유효하지 않은 시간입니다.');
                        newWindow.close();
                        return;
                    }

                    // 기존 날짜 인덱스 찾기
                    const existingDateIndex = timeSlots.findIndex((item) => item.date === date);

                    if (existingDateIndex !== -1) {
                        // 동일한 날짜 내 중복 시간 확인
                        const isDuplicateTime = timeSlots[existingDateIndex].times.some(
                            (time) =>
                                time.hour === times[0].hour &&
                                time.minute === times[0].minute
                        );

                        if (isDuplicateTime) {
                            alert('이미 동일한 시간이 추가되었습니다.');
                        } else {
                            setTimeSlots((prevTimeSlots) => {
                                const updatedTimeSlots = [...prevTimeSlots];
                                updatedTimeSlots[existingDateIndex].times.push({
                                    hour: times[0].hour,
                                    minute: times[0].minute,
                                });
                                return updatedTimeSlots;
                            });
                        }
                    } else {
                        // 동일한 날짜 확인
                        const isDuplicateDate = timeSlots.some((item) => item.date === date);

                        if (isDuplicateDate) {
                            alert('이미 동일한 날짜가 추가되었습니다.');
                        } else {
                            setTimeSlots((prevTimeSlots) => [...prevTimeSlots, { date, times }]);
                        }
                    }

                    newWindow.close();
                }}
            />,
            newWindow.document.body
        );
    };

    // 날짜 기준 일정 삭제
    const handleRemoveDateTime = (dateToRemove) => {
        // Filter out the selected date
        const updatedTimeSlots = timeSlots.filter((dateTime) => dateTime.date !== dateToRemove);
        setTimeSlots(updatedTimeSlots);
    };

    // 일별 시간 기준 일정 삭제
    const handleRemoveTime = (dateToRemove, timeToRemove) => {
        const dateIndex = timeSlots.findIndex((dateTime) => dateTime.date === dateToRemove);

        if (dateIndex !== -1) {
            const updatedTimeSlots = [...timeSlots];
            const timeIndex = updatedTimeSlots[dateIndex].times.findIndex(
                (time) => time.hour === timeToRemove.hour && time.minute === timeToRemove.minute
            );

            if (timeIndex !== -1) {
                updatedTimeSlots[dateIndex].times.splice(timeIndex, 1);

                if (updatedTimeSlots[dateIndex].times.length === 0) {
                    updatedTimeSlots.splice(dateIndex, 1);
                }

                setTimeSlots(updatedTimeSlots);
            }
        }
    };

    // 일정 전체 삭제
    const handleRemoveAllTimeSlots = () => {
        setTimeSlots([]);
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

    // 좌석 추가 버튼
    const handleAddButtonClick = () => {
        const newWindow = window.open('', '_blank', 'width=400,height=240,left=100,top=100');

        ReactDOM.render(
            <SeatModalContent
                onSubmit={(enteredValues) => {
                    const formattedValues = enteredValues.reduce((acc, curr) => {
                        const formattedValue =
                            curr.label === '타입' ? String(curr.value) : parseInt(curr.value, 10) || 0;
                        acc.push({ label: curr.label, value: formattedValue });
                        return acc;
                    }, []);

                    const isDuplicateType = seatValues.some((values) => values[0].value === formattedValues[0].value);

                    if (isDuplicateType) {
                        alert('이미 동일한 타입의 좌석이 추가되었습니다.');
                        newWindow.close(); // Close the window only if it's a duplicate type
                    } else {
                        setSeatValues((prevSeatValues) => [...prevSeatValues, formattedValues]);
                        newWindow.close(); // Close the window when it's not a duplicate type
                    }
                }}
            />,
            newWindow.document.body
        );
    };

    // 좌석 삭제
    const handleRemoveSeat = (typeToRemove) => {
        const updatedSeatValues = seatValues.filter((seat) => seat[0].value !== typeToRemove);
        setSeatValues(updatedSeatValues);
    };

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    return (
        <Container>
            <div>
                <H1>공연 등록</H1>
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
                                    disabled={hasExistingSchedules} // 일정이 있으면 비활성화
                                />
                                <CalenderDiv onClick={() => startDatePickerRef.current && startDatePickerRef.current.setOpen(true)}>
                                    <CalendarSVG />
                                </CalenderDiv>
                                <P>하단의 일정을 등록한 상태에서는 달력이 비활성화 됩니다.</P>
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
                                    disabled={hasExistingSchedules} // 일정이 있으면 비활성화
                                />
                                <CalenderDiv onClick={() => endDatePickerRef.current && endDatePickerRef.current.setOpen(true)}>
                                    <CalendarSVG />
                                </CalenderDiv>
                                <P>
                                    수정이 필요할 경우 일정을 전부 삭제해주세요.
                                    <Button2 onClick={handleRemoveAllTimeSlots}>
                                        일정 전체 삭제
                                    </Button2>
                                </P>
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
                                <option value="공연예정">공연예정</option>
                                <option value="진행중">진행중</option>
                                <option value="공연종료">공연종료</option>
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
                <Button>공연 등록</Button>
            </CustomDiv>
            <div>
                <H1>일정 등록</H1>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="1">일별 시작 시간</Th>
                        <Td>
                            <div>
                                <Button1 onClick={handleAddTimeButtonClick}>
                                    일정추가
                                </Button1>
                            </div>
                        </Td>
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
                                        <ButtonX onClick={() => handleRemoveDateTime(dateTime.date)}>
                                            ✕
                                        </ButtonX>
                                    </BetweenDiv>
                                </Th>
                                <Td>
                                    <FlexCenterDiv>
                                        {dateTime.times.map((time, timeIndex) => (
                                            <TimeDiv key={timeIndex} >
                                                <WidthDiv>{`${time.hour}:${time.minute}`}</WidthDiv>
                                                <ButtonX onClick={() => handleRemoveTime(dateTime.date, time)}>
                                                    ✕
                                                </ButtonX>
                                            </TimeDiv>
                                        ))}
                                    </FlexCenterDiv>
                                </Td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </Table>
            </div>
            <CustomDiv>
                <Button>일정 등록</Button>
            </CustomDiv>
            <div>
                <H1>좌석 등록</H1>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="1">좌석</Th>
                        <Td>
                            <Button1 onClick={handleAddButtonClick}>
                                추가
                            </Button1>
                        </Td>
                    </tr>
                    {seatValues.map((value, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <Th>
                                    <BetweenDiv>
                                        {`타입 : ${value[0].value}석`}
                                        <ButtonX onClick={() => handleRemoveSeat(value[0].value)}>
                                            ✕
                                        </ButtonX>
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
            <div style={{ padding: '20px 0 0 0' }}>
                <Button>좌석 등록</Button>
            </div>
        </Container>
    );
};

export default AdminCreateShow;
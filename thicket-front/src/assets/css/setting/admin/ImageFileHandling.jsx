import { useState, useRef } from 'react';

const ImageFileHandling = () => {
    const [uploadedFiles, setUploadedFiles] = useState([]);
    const [selectedImage, setSelectedImage] = useState(null);
    const fileInputRef = useRef(null);
    const [uploadedDetailImages, setUploadedDetailImages] = useState([]);
    const [selectedDetailImage, setSelectedDetailImage] = useState(null);
    const detailImageInputRef = useRef(null);

    // 공연포스터 이미지 업로드
    const handleFileUpload = (e) => {
        const file = e.target.files[0];
        if (uploadedFiles.length >= 1) {
            alert('공연포스터 이미지는 1개만 등록 가능합니다. 삭제 후 등록해 주세요.');
            return;
        }
        setUploadedFiles((prevFiles) => [...prevFiles, file]);
    };

    // 공연포스터 이미지 삭제
    const handleRemoveFile = () => {
        setUploadedFiles([]);
        fileInputRef.current.value = null;
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
        const file = e.target.files[0];
        setUploadedDetailImages((prevImages) => [...prevImages, file]);
    };

    // 상세페이지 이미지 삭제
    const handleRemoveDetailImage = (index) => {
        setUploadedDetailImages((prevImages) => {
            const newImages = [...prevImages];
            newImages.splice(index, 1);
            console.log(newImages)
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

    return {
        uploadedFiles,
        selectedImage,
        fileInputRef,
        handleFileUpload,
        handleRemoveFile,
        handleImageClick,
        handleCloseModal,
        uploadedDetailImages,
        selectedDetailImage,
        detailImageInputRef,
        handleDetailImageUpload,
        handleRemoveDetailImage,
        handleDetailImageClick,
        handleCloseDetailImageModal,
    };
};

export default ImageFileHandling;
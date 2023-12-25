export const Dropdown = ({ value, setIdentify, setIsOpen, isOpen }) => {
    const ValueClick = () => {
        setIdentify(value)
        setIsOpen(!isOpen)
    }
    return(
        <li onClick={ValueClick}>{value}</li>
    )
}
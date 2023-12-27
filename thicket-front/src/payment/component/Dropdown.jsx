export const Dropdown = ({ value, setIdentify, setIsOpen, isOpen }) => {
    const ValueClick = () => {
        setIdentify(value)
        setIsOpen(!isOpen)
    }
    return(
        <ul onClick={ValueClick}>{value}</ul>
    )
}
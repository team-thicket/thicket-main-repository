import styled from "styled-components";

const Wrapper = styled.a`
  padding-top: 5px;
  height: 30px;
  margin: 10px;
  font-size: 20px;
  display: block;
  border: black solid 1px;
  border-radius: 5px;
  text-align: center;
  background-color: beige;
  &:hover {
    background-color: lightgray; /* 색상 */
    cursor: pointer;
  }
`
const Menu = ({ name, link, onClick }) => {
    return (
        <Wrapper href={link} onClick={onClick} name={link}>
            {name}
        </Wrapper>
    );
};

export default Menu;
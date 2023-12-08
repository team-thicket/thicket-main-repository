import styled from "styled-components";

const Wrapper = styled.a`
  //padding-top: 5px;
  height: 30px;
  line-height: 30px; /* 텍스트의 높이에 따라 상하 가운데 정렬 */
  margin: 10px;
  padding: 0px 10px;
  font-size: 16px;
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
const HeaderMenu = ({ name, link, onClick }) => {
    return (
        <Wrapper href={link} onClick={onClick} name={link}>
            {name}
        </Wrapper>
    );
};

export default HeaderMenu;
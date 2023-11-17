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
`
export default function Menu({name,link}) {
    return<Wrapper href={link}>{name}</Wrapper>
}
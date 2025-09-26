/**
 * MUI 
 * https://mui.com/
 * 
 * 1) 설치
 *      ` npm install @mui/joy @emotion/react @emotion/styled `
 * 
 * 2) 사용
 *  ※ 항상 공문을 참고하자!!     
 *  사용하려는 Component를 반드시 import
 * 
 * React textarea 라이브러리
 * https://tiptap.dev/
 * 
 * 
 * 순수 CSS 요소 검색 및 제공
 * https://codepen.io/
 * 
 * ====================
 * CSS 적용방법
 *  1. CSS 파일 import
 *  2. 마크업·컴포넌트에 **CSS 객체**로 기재
 *    ` <div style={ { width:"25%" } } > `
 * ====================
 * 구글 - MUI meterial icon 
 * https://fonts.google.com/icons?icon.set=Material+Icons
 * 
 * 
 * ====================
 * Navigation menu : header
 */

import Button from '@mui/joy/Button';
import Box from '@mui/joy/Box';
import Input from '@mui/joy/Input';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import Switch from '@mui/joy/Switch';
import * as React from 'react';
import Avatar from '@mui/joy/Avatar';
import Badge from '@mui/joy/Badge';
import Typography from '@mui/joy/Typography';
import SideBar from './SideBar';

export default function Component14(props) {

    const handleChange = (event, newValue) => {
        alert(`You chose "${newValue}"`);
    };

    const [checked, setChecked] = React.useState(false);

    return (<>
        <div>
            <div> Component14 </div>

            <h2>1</h2>
            <div>소문자 마크업 : html</div>
            <div>대문자 마크업 : react Component</div>
            <Button variant="solid">Hello world</Button>

            <h2>2</h2>
            <div></div>
            <Button
                color="primary"
                disabled={false}
                loading={false}
                onClick={function () { }}
                size="sm"
                variant="outlined"
            >내가 만든 버튼</Button>
            <br />
            <br />
            <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
                <Button>Button</Button>
                <Button disabled>Disabled</Button>
                <Button loading>Loading</Button>
            </Box>

            <h2>3</h2>
            <div>Input box</div>
            <Input placeholder="Type in here…" />

            <h2>4</h2>
            <div>Select box</div>
            <Select defaultValue="dog" onChange={handleChange}>
                <Option value="dog">Dog</Option>
                <Option value="cat">Cat</Option>
                <Option value="fish">Fish</Option>
                <Option value="bird">Bird</Option>
            </Select>

            <h2>5</h2>
            <div>Switch</div>
            <Switch
                checked={checked}
                onChange={(event) => setChecked(event.target.checked)}
            />

            <h2>6</h2>
            <div>Avatar</div>
            <div>참고 style= 중괄호 두겹 CSS </div>
            <br />
            <br />

            <Box sx={{ display: 'flex', gap: 2 }}>
                <Badge badgeContent={1} variant="solid">
                    <Typography sx={{ fontSize: 'xl' }}>  <Avatar /> </Typography>
                </Badge>

                <Avatar>JG</Avatar>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
            </Box>

            <h2>7</h2>
            <div>List - Collapsible list</div>
            <SideBar />

            <div> Skeleton - 화면 출력 전 구역을 로딩하는 것</div>

            <div>Snackbar - 사이드 하단 팝업창 </div>

            <div>card</div>

        </div>
    </>)
}
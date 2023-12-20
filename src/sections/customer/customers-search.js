import MagnifyingGlassIcon from '@heroicons/react/24/solid/MagnifyingGlassIcon';
import { SearchOutlined } from '@mui/icons-material';
import { Button, Card, InputAdornment, MenuItem, OutlinedInput, Select, SvgIcon, TextField } from '@mui/material';
import { format } from 'date-fns';
import { useCallback } from 'react';
export const CustomersSearch = (props) => {

  const previousDate = format(new Date().getTime()-(10 * 24 * 60 * 60 * 1000), 'yyyy-MM-dd')
  const currentDate = format(new Date().getTime(), 'yyyy-MM-dd')



  const handleSubmit = (e)=>{
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form)
    const data = {
      searchKey : formData.get("searchKey"),
      fromDate : new Date(formData.get("fromDate")).getTime(),
      toDate : new Date(formData.get("toDate")).getTime(),
      userType : formData.get("userType") !== "A" ? formData.get("userType") : null
    }
    props.onSearch(data);
  }
  

 return (<Card sx={{ p: 2 }}>
    <form onSubmit={(e)=>{handleSubmit(e)}}>
    <OutlinedInput
      defaultValue=""
      fullWidth
      placeholder="Search customer"
      name='searchKey'
      startAdornment={(
        <InputAdornment position="start" >
          
          <SvgIcon
            color="action"
            fontSize="small"
          >
            <MagnifyingGlassIcon />
          </SvgIcon>
        </InputAdornment>
      )}
      sx={{ maxWidth: 240 }}
    />


          { props.userType === "A" && <Select
                sx={{minWidth:200}}
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name='userType'
                defaultValue="A"
                label="User type"
              >
                <MenuItem value={"A"}>All</MenuItem>
                <MenuItem value={"S"}>Staff</MenuItem>
                <MenuItem value={"W"}>Wholesaler</MenuItem>
                <MenuItem value={'R'}>Retailer</MenuItem>
              </Select>}

      <TextField
        sx={{minWidth:200}}
        id="datetime-local"
        label="From Date"
        type="date"
        name='fromDate'
        defaultValue={previousDate}
        InputLabelProps={{
          shrink: true,
        }}
      />

      
    <TextField
        sx={{minWidth:200}}
        id="datetime-local"
        label="To Date"
        type="date"
        defaultValue={currentDate}
        name='toDate'
        InputLabelProps={{
          shrink: true,
        }}
      />

      <Button type='submit' sx={{mx:2}} startIcon={(
                    <SvgIcon fontSize="small">
                      <SearchOutlined />
                    </SvgIcon>
                  )}
                  variant="contained"> Search </Button>
    </form>
  </Card>
)};

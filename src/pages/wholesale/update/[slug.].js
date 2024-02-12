const { Label } = require("@mui/icons-material")
const { FormControl } = require("@mui/material")
import { Layout as DashboardLayout } from 'src/layouts/dashboard/layout';


const UpdateWholesale = () =>{


    return (<>
        <form>
            <FormControl defaultValue="" required>
                <Label>Name</Label>
                <StyledInput placeholder="Write your name here" />
                <HelperText />
            </FormControl>
        </form>
    </>)


}
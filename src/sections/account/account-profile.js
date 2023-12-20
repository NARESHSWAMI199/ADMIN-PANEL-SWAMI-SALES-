import {
  Avatar,
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  Divider,
  Typography
} from '@mui/material';
import { format } from 'date-fns';

export const AccountProfile = (props) => {
  const user = props.user
  const createdAt = (!!user && !!user.createdAt) ?  format(user.createdAt, 'dd/MM/yyyy') : "";
  return (
          <Card>
            <CardContent>
              <Box
                sx={{
                  alignItems: 'center',
                  display: 'flex',
                  flexDirection: 'column'
                }}
              >
                <Avatar
                  src={!!user ? user.avatar : ''}
                  sx={{
                    height: 80,
                    mb: 2,
                    width: 80
                  }}
                />
                <Typography
                  gutterBottom
                  variant="h5"
                >
                  {!!user ? user.username :''}
                </Typography>
                <Typography
                  color="text.secondary"
                  variant="body2"
                >
                 Email : {!!user ? user.email:""} 
                </Typography>
                <Typography
                  color="text.secondary"
                  variant="body2"
                >
                  Last updated at : {createdAt}
                </Typography>
              </Box>
            </CardContent>
            <Divider />
            <CardActions>
              <Button
                fullWidth
                variant="text"
              >
                Upload picture
              </Button>
            </CardActions>
          </Card>
        )
  }
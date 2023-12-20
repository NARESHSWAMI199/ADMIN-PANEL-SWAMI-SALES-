import Head from 'next/head';
import { subDays, subHours } from 'date-fns';
import { Box, Container, Unstable_Grid2 as Grid, Link } from '@mui/material';
import { Layout as DashboardLayout } from 'src/layouts/dashboard/layout';
import {OverviewUsers } from 'src/sections/overview/overview-users';
import { OverviewLatestOrders } from 'src/sections/overview/overview-latest-orders';
import { OverviewLatestProducts } from 'src/sections/overview/overview-latest-products';
import { OverviewSales } from 'src/sections/overview/overview-sales';
import { OverviewTraffic } from 'src/sections/overview/overview-traffic';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useAuth } from 'src/hooks/use-auth';
import { host } from 'src/utils/util';




const now = new Date();

const Page = (props) =>  {
const [wholesales,setWholesales] = useState([])
const [dashboardData,setDashboardData] = useState({
  users : {},
  retailers : {},
  wholesalers : {},
  staffs : {}
})    
const auth = useAuth()
useEffect( ()=>{
    const getData = async () => {
      axios.defaults.headers = {
        Authorization : auth.token
      }
      await axios.post(host+"/admin/store/all",{})
      .then(res => {
          const data = res.data.content;
          setWholesales(data);
      })
      .catch(err => {
        console.log(err)
      } )
    }
    getData();

  },[])


  useEffect( ()=>{
    const getData = async () => {
      axios.defaults.headers = {
        Authorization : auth.token
      }
      await axios.get(host+"/admin/dashboard/")
      .then(res => {
          const data = res.data;
          setDashboardData(data)
      })
      .catch(err => {
        console.log(err)
      } )
    }
    getData();

  },[])

  return ( <>
    <Head>
      <title>
        Overview | Swami sales
      </title>
    </Head>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 8
      }}
    >
      <Container maxWidth="xl">
        <Grid
          container
          spacing={3}
        >
          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
           <Link href="/users" sx={{textDecoration:'none'}} > <OverviewUsers
              title="USERS"
              sx={{ height: '100%' }}
              value={dashboardData.users}
            /> </Link>
          </Grid>


          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
          <Link sx={{
            textDecoration:'none'
           }} href="/customers" > <OverviewUsers
               title="RETAILERS"
              sx={{ height: '100%' }}
              value={dashboardData.retailers}
            /> </Link>
          </Grid>



          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
          <Link sx={{
            textDecoration:'none'
           }} href="/wholesalers" >  <OverviewUsers
              title="WHOLESALERS"
              sx={{ height: '100%' }}
              value={dashboardData.wholesalers}
            /> </Link>
          </Grid>



          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
          <Link sx={{
            textDecoration:'none'
           }} href="/staffs" >  <OverviewUsers
              title='STAFFS'
              sx={{ height: '100%' }}
              value={dashboardData.staffs}
            /> </Link>
          </Grid>

 {/*          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
            <OverviewTotalCustomers
              difference={16}
              positive={false}
              sx={{ height: '100%' }}
              value="1.6k"
            />
          </Grid>
          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
            <OverviewTasksProgress
              sx={{ height: '100%' }}
              value={75.5}
            />
          </Grid> 
          <Grid
            xs={12}
            sm={6}
            lg={3}
          >
            <OverviewTotalProfit
              sx={{ height: '100%' }}
              value="$15k"
            />
          </Grid>*/}
          <Grid
            xs={12}
            lg={8}
          >
            <OverviewSales
              chartSeries={[
                {
                  name: 'This year',
                  data: [18, 16, 5, 8, 3, 14, 14, 16, 17, 19, 18, 20]
                },
                {
                  name: 'Last year',
                  data: [12, 11, 4, 6, 2, 9, 9, 10, 11, 12, 13, 13]
                }
              ]}
              sx={{ height: '100%' }}
            />
          </Grid>
          <Grid
            xs={12}
            md={6}
            lg={4}
          >
            <OverviewTraffic
              chartSeries={[63, 15, 22]}
              labels={['Desktop', 'Tablet', 'Phone']}
              sx={{ height: '100%' }}
            />
          </Grid>
          <Grid
            xs={12}
            md={6}
            lg={4}
          >
         <OverviewLatestProducts
              products = {wholesales }
              sx={{ height: '100%' }}
            />
          </Grid>
          <Grid
            xs={12}
            md={12}
            lg={8}
          >
            <OverviewLatestOrders
              orders={[
                {
                  id: 'f69f88012978187a6c12897f',
                  ref: "naresh swami",
                  amount: 30.5,
                  customer: {
                    name: 'Ekaterina Tankova'
                  },
                  createdAt: 1555016400000,
                  status: 'pending'
                },
                {
                  id: '9eaa1c7dd4433f413c308ce2',
                  ref: 'DEV1048',
                  amount: 25.1,
                  customer: {
                    name: 'Cao Yu'
                  },
                  createdAt: 1555016400000,
                  status: 'delivered'
                },
                {
                  id: '01a5230c811bd04996ce7c13',
                  ref: 'DEV1047',
                  amount: 10.99,
                  customer: {
                    name: 'Alexa Richardson'
                  },
                  createdAt: 1554930000000,
                  status: 'refunded'
                },
                {
                  id: '1f4e1bd0a87cea23cdb83d18',
                  ref: 'DEV1046',
                  amount: 96.43,
                  customer: {
                    name: 'Anje Keizer'
                  },
                  createdAt: 1554757200000,
                  status: 'pending'
                },
                {
                  id: '9f974f239d29ede969367103',
                  ref: 'DEV1045',
                  amount: 32.54,
                  customer: {
                    name: 'Clarke Gillebert'
                  },
                  createdAt: 1554670800000,
                  status: 'delivered'
                },
                {
                  id: 'ffc83c1560ec2f66a1c05596',
                  ref: 'DEV1044',
                  amount: 16.76,
                  customer: {
                    name: 'Adam Denisov'
                  },
                  createdAt: 1554670800000,
                  status: 'delivered'
                }
              ]}
              sx={{ height: '100%' }}
            />
          </Grid>
        </Grid>
      </Container>
    </Box>
  </>
  
  );
 }

Page.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Page;

import { datag } from '../datag';
import axios from 'axios';
import { layer } from '@layui/layui-vue';



export const addroom={
    async api(_roomid:any,_room:any,_msize:any,_roomsta:any,_airconditioning:any,_wifi:any){
        const lxxapi = datag.api+"/room/add";
        const lxxdata = {
            roomid: _roomid,
			room: _room,
			msize: _msize,
			roomsta: _roomsta,
			airconditioning: _airconditioning,
			wifi: _wifi
        }
        const headers = {
            method:"GET",
           
            headers:{"Content-Type":"application/json",},
            body:JSON.stringify(lxxdata)
        }
       try{
          const response = await axios.post(lxxapi,headers)
          console.log(response.data)
       }catch(error){
        console.log(error)
       }
    }
    
}


export const deleroom = {
    async api(_roomid:any){
        const lxxapi = datag.api+"/room/dele"
        const lxxdata ={
            roomid:_roomid
        }
        const headers = {
            method:"POST",
            headers:{
                headers:{"Content-Type":"application/json",},
                body:JSON.stringify(lxxdata)
            }
        }
        console.log(lxxdata)
        try{

            const response = await axios.post(lxxapi,lxxdata)
            console.log(response.data)
        }catch(error){
            console.log(error)
        }
    }
}

export const markuproom = {
    async api(_roomid:any,_room:any,_msize:any,_roomsta:any,_airconditioning:any,_wifi:any){
        const lxxapi = datag.api+"/room/markup"
        const lxxdata={
            roomid: _roomid,
			room: _room,
			msize: _wifi,
			roomstat: _roomsta,
			airconditioning: _airconditioning,
			wifi:_msize
        }
        console.log(lxxdata)
        const headers = {
            method:"POST",
            headers:{"Content-Type":"application/json",},
            body:JSON.stringify(lxxdata)
        }
       try{
            const response = await axios.post(lxxapi,lxxdata)
            console.log(response)
       }catch(error){
            console.log(error);
       }
    }

}

export const bookroom={
    async api(){
        const api = datag.api+"/book/all"
        const headers = {
            method:"GET",
            headers:{"Content-Type":"application/json",}
        }
        try{
            const response = await axios.get(api,headers)
            console.log(response.data)
           datag.allbookroom.value = response.data.data
          
        }catch(error){
            console.log(error)
        }
    }
         
    }

export const delebookroom={
    async api(_roomid:any){
        const api = datag.api+"/book/dele"
    const lxx={
        roomid1:_roomid,
    }
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
        }catch(error){
            console.log(error)
        }
    }
        
    }


export const delebookserve={
    async api(_serveid:any){
        const api = datag.api+"/serve/dele"
    const lxx={
        serveid:_serveid,
    }
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            lxxallserve.api()
        }catch(error){
            console.log(error)
        }
    }
        
    }
    
export const addbook={
    async api(_payid:any,_money:any,_peydata:any,_userid:any){
        const api = datag.api+"/recording/add"
    const lxx={
        payid:_payid,
        money:_money,
        peydata:_peydata,
        userid:_userid
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("此单成功",{icon:1})
                lxxallserve.api()
            }
        }catch(error){
            console.log(error)
        }
    }
        
    }


export const deleuser={
    async api(_userid:any){
        const api = datag.api+"/user/dele"
    const lxx={
        userid:_userid,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("删除成功",{icon:1})
                lxxalluser.api()
            }
        }catch(error){
            console.log(error)
            layer.msg("删除失败",{icon:2})
        }
    }
        
}

export const delerecording={
    async api(_userid:any){
        const api = datag.api+"/recording/dele"
    const lxx={
        userid:_userid,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
                lxxallpay.api()
                lxxallre.api()
            }
        }catch(error){
            console.log(error)
            layer.msg("s失败",{icon:2})
        }
    }
        
}

export const addall={
    async api(_userid:any,_allmoney:any){
        const api = datag.api+"/allpay/add"
    const lxx={
        allid:_userid,
        allmoney:_allmoney
    }
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
                lxxallpay.api()
                lxxallre.api()
            }
        }catch(error){
            console.log(error)
            layer.msg("s失败",{icon:2})
        }
    }
}


export const adddele={
    async api(_userid:any){
        const api = datag.api+"/allpay/dele"
    const lxx={
        allid:_userid,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
            }
        }catch(error){
            console.log(error)
            layer.msg("s失败",{icon:2})
        }
    }
        
}

export const addbookroom={
    async api(_userid:any,_roomid:any,_booktime:any){
        const api = datag.api+"/book/dele"
    const lxx={
        userid1:_userid,
        roomid1:_roomid,
        booktime:_booktime,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
            }
        }catch(error){
            console.log(error)
            layer.msg("失败",{icon:2})
        }
    }
        
}

export const updateuser={
    async api(_userid:any,_roomid:any,_booktime:any){
        const api = datag.api+"/user/markup"
    const lxx={
        userid:_userid,
        name:_roomid,
        phone:_booktime,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
            }
        }catch(error){
            console.log(error)
            layer.msg("失败",{icon:2})
        }
    }
        
}


export const updateuserroom={
    async api(_userid:any,_roomid:any,_booktime:any,_phone:any){
        const api = datag.api+"/user/update"
    const lxx={
        userid:_userid,
        name:_roomid,
        bookinfo:_booktime,
        phone:_phone,
    }
    console.log(lxx)
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
                lxxalluser.api()
            }
        }catch(error){
            console.log(error)
            layer.msg("失败",{icon:2})
        }
    }
        
}

export const lxxallroom = {
   async api(){
        const lxx = datag.api +"/room/all"
            const headers = {
                method:"GET",
                headers:{"Content-Type":"application/json",}
            }
            try{
            const response = await axios.get(lxx,headers);
            datag.allroom.value = response.data.data 
            }catch(error){
                console.log(error)
            }
    }
}


export const lxxallbook = {
async api(){
        const lxx = datag.api +"/book/all"
            const headers = {
                method:"GET",
                headers:{"Content-Type":"application/json",}
            }
            try{
            const response = await axios.get(lxx,headers);
            datag.allbook.value = response.data.data 
            }catch(error){
                console.log(error)
        }
    }
}




export const lxxalluser = {
async api(){
    const lxx = datag.api +"/user/all"
    const headers = {
        method:"GET",
        headers:{"Content-Type":"application/json",}
    }
    try{
    const response = await axios.get(lxx,headers);
    console.log(response.data.data)
    datag.alluser.value = response.data.data 
    }catch(error){
        console.log(error)
    }
  }
}

export const lxxallserve = {
    async api(){
        const lxx = datag.api +"/serve/all"
        const headers = {
            method:"GET",
            headers:{"Content-Type":"application/json",}
        }
        try{
        const response = await axios.get(lxx,headers);
        datag.allserve.value = response.data.data 
        }catch(error){
            console.log(error)
        }
      }
    }

export const lxxallpay ={
    async api(){
        const lxx = datag.api +"/allpay/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             datag.allpay.value = response.data.data 
             }catch(error){
                 console.log(error)
             }
    }
}

export const lxxallre ={
    async api(){
        const lxx = datag.api +"/recording/all"
        const headers = {
            method:"GET",
            headers:{"Content-Type":"application/json",}
        }
        try{
        const response = await axios.get(lxx,headers);
        console.log(response.data.data)
        datag.allre.value = response.data.data 
        }catch(error){
            console.log(error)
        }
    }
}

export const addbookf={
    async api(_userid:any,_roomid:any,_bookdate:any){
        const api = datag.api+"/chabook/add"
    const lxx={
        userid:_userid,
        roomid:_roomid,
        bookdate:_bookdate
    }
        try{
            const response = await axios.post(api,lxx)
            console.log(response.data)
            if (response.data.code ==20000) {
                layer.msg("成功",{icon:1})
            }
        }catch(error){
            console.log(error)
            layer.msg("失败",{icon:2})
        }
    }
}
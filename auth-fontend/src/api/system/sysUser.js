import request from '@/utils/request'

//常量
const api_name = '/api/system/sysUser'
export default {
  //列表
  getList(page,limit,searchObj){
    return request({
      //接口路径
      // url: '/api/system/sysRole/'+page+'/'+limit,
      url: `${api_name}/${page}/${limit}`,
      method: 'get', //提交方式
      //参数
      params: searchObj
    })
  },
  //添加
  save(user) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: user
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/findUserById/${id}`,
      method: 'get'
    })
  },
  updateById(user) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: user
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  //更新用户状态
  updateStatus(id, status) {
    return request({
      url: `${api_name}/updateStatus/${id}/${status}`,
      method: 'get'
    })
  },
}

import request  from "@/utils/request";

//常量
const api_name = '/api/system/sysRole'

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
  removeById(id) {
    return request({
      url: `${api_name}/remove/${id}`,
      method: 'delete'
    })
  },
  save(role) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: role
    })
  },
  //编辑
  getById(id) {
    return request({
      url: `${api_name}/findRoleById/${id}`,
      method: 'get'
    })
  },
  //更新
  updateById(role) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: role
    })
  },
  batchRemove(idList) {
    return request({
      url: `${api_name}/batchRemove`,
      method: `delete`,
      data: idList
    })
  },
}

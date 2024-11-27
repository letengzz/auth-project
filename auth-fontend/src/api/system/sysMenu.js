import request  from "@/utils/request";

/*
菜单管理相关的API请求函数
*/
const api_name = '/api/system/sysMenu'

export default {
  /*
  获取权限(菜单/功能)列表
  */
  findNodes() {
    return request({
      url: `${api_name}/findNodes`,
      method: 'get'
    })
  },
  //列表
  getList(page,limit,searchObj){
    return request({
      //接口路径
      // url: '/api/system/sysRole/'+page+'/'+limit,
      url: `${api_name}/${page}/${limit}`,
      method: 'get', // 提交方式
      // 参数
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
  //根据用户id查询用户已分配的角色
  getRolesByUserId(userId) {
    return request({
      url: `${api_name}/toAssign/${userId}`,
      method: 'get'
    })
  },

//分配角色
  assignRoles(assignRoleVo) {
    return request({
      url: `${api_name}/doAssign`,
      method: 'post',
      data: assignRoleVo
    })
  },
  /*
查看某个角色的权限列表
*/
  toAssign(roleId) {
    return request({
      url: `${api_name}/toAssign/${roleId}`,
      method: 'get'
    })
  },

  /*
  给某个角色授权
  */
  doAssign(assginMenuVo) {
    return request({
      url: `${api_name}/doAssign`,
      method: "post",
      data: assginMenuVo
    })
  }
}

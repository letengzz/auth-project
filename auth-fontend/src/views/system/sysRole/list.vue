<script>
//引入定义接口js文件
import api from '@/api/system/sysRole'

const defaultForm = {
  id: '',
  roleName: '',
  roleCode: ''
}
export default {
  name: "sysRole",
  //定义初始值
  data() {
    return {
      list: [], //角色列表
      total: 0, //总记录数
      page: 1, //当前页
      limit: 5, //每页记录数
      searchObj: {}, //条件封装对象
      listLoading:true, // 数据是否正在加载

      dialogVisible: false,
      sysRole: defaultForm,
      saveBtnDisabled: false,
      multipleSelection: []// 批量删除选中的记录列表
    }
  },
  //页面渲染之前获取数据
  created() {
    //调用列表方法
    this.fetchData();
  },
  //定义方法
  methods:{ //具体方法
    //条件分页查询列表
    //pageNum 查询页数
    fetchData(pageNum = 1){
      //页数赋值
      this.page = pageNum;
      //ajax
      api.getList(this.page,this.limit,this.searchObj)
        .then(response =>{
          console.log(response)
          this.listLoading =false // 数据是否正在加载
          //每页数据列表
          this.list = response.data.list;
          //总记录数
          this.total = response.data.total;
        })
    },
    handleSizeChange(size = 5) {
      this.limit = size;
      this.fetchData()
    },
    // 重置表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {}
      this.fetchData()
    },
    removeDataById(id) {
      console.log(id)
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {// promise
        // 点击确定，远程调用ajax
        return api.removeById(id)
      }).then((response) => {
        this.fetchData(this.list.length > 1 ? this.page : this.page-1)
        this.$message.success(response.message || {
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //弹出添加的表单
    add(){
      this.dialogVisible = true
      this.sysRole = {}
    },
    //添加或更新
    saveOrUpdate() {
      if (!this.sysRole.id) {
        this.save()
      } else {
        this.update()
      }
    },

    //添加
    save() {
      api.save(this.sysRole).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },
    //编辑
    edit(id) {
      this.dialogVisible = true
      api.getById(id).then(response => {
        this.sysRole = response.data
      })
    },
    //更新
    update() {
      api.updateById(this.sysRole).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },
    // 当多选选项发生变化的时候调用
    handleSelectionChange(selection) {
      console.log(selection)
      this.multipleSelection = selection
    },
    // 批量删除
    batchRemove() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('请选择要删除的记录！')
        return
      }
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 点击确定，远程调用ajax
        // 遍历selection，将id取出放入id列表
        var idList = []
        this.multipleSelection.forEach(item => {
          idList.push(item.id)
        })
        // 调用api
        return api.batchRemove(idList)
      }).then((response) => {
        this.fetchData()
        this.$message.success(response.message)
      }).catch(error => {
        if (error === 'cancel') {
          this.$message.info('取消删除')
        }
      })
    }
  }
}
</script>

<template>
  <div class="app-container">
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item style="width: 100%" label="角色名称">
              <el-input v-model="searchObj.roleName" placeholder="角色名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchData()">查询</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">清空</el-button>
        </el-row>
      </el-form>
    </div>
    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" plain icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
      <el-button class="btn-add" size="mini" @click="batchRemove()" >批量删除</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 18px;"
      height="360"
      @selection-change="handleSelectionChange"
      :default-sort = "{prop: 'createTime', order: 'ascending'}"
    >
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column
        prop="roleName"
        label="角色名称"
      >
      </el-table-column>
      <el-table-column
        prop="roleCode"
        label="角色编码"
      >
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间" width="160" >
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="paging-div">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="fetchData"
        :current-page="page"
        :page-sizes="[5, 10, 20, 100]"
        :page-size="limit"
        layout="prev, pager, next,total,sizes,jumper"
        :total="total"
        style="margin-top: 30px;text-align: center">
      </el-pagination>
    </div>
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%" >
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="角色编码">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
.search-div {
  padding:10px;border: 1px solid #EBEEF5;border-radius:3px;
}
.tools-div {
  margin-top: 10px;padding:10px;border: 1px solid #EBEEF5;border-radius:3px;
}
</style>

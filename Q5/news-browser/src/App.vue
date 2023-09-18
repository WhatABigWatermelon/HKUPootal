<template>
  <el-container>
    <el-main>
      <el-card v-for="news in newsList">
        <el-row>
          <el-col :span="16">
            <el-row>
              <el-link :underline="true" :href="news.url">{{
                news.title
              }}</el-link>
            </el-row>
            <el-row>
              <el-col :span="12">{{ news.source }}</el-col>
              <el-col :span="12">{{ news.author }}</el-col>
            </el-row>
            <el-row>{{ news.description }}</el-row>
            <el-row>{{ news.published_at }}</el-row>
          </el-col>
          <el-col :span="8">
            <el-image :src="news.image" :fit="fill" />
          </el-col>
        </el-row>
      </el-card>
    </el-main>
  </el-container>
</template>

<script>
import axios from 'axios'
import { Picture as IconPicture } from '@element-plus/icons-vue'
export default {
  name: 'app',
  data() {
    return {
      newsList: new Array(),
      loadFinish: false,
    }
  },
  created() {
    axios
      .get(
        'https://proxy-for-test-2023.hkupootal.com/?q=http%3A%2F%2Fapi.mediastack.com%2Fv1%2Fnews%3Faccess_key%3D2fd2f9887688bad65a8d02ffdbd47b52'
      )
      .then((response) => {
        this.newsList = response.data.data
        console.log(this.newsList)
      })
      .catch((error) => {
        console.log(error)
      })
  },
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>

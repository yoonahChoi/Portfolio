<template>
  <div class="table-wrap">
    <table class="board-table">
      <colgroup>
        <col width="8%"/>
        <col width="10%"/>
        <col width="*"/>
        <col width="10%"/>
        <col width="15%"/>
        <col width="8%"/>
        <col width="8%"/>
      </colgroup>
      <thead class="table-head" :class="$mq">
        <tr>
          <th><span>번호</span></th>
          <th><span>분류</span></th>
          <th><span>제목</span></th>
          <th><span>글쓴이</span></th>
          <th><span>날짜</span></th>
          <th><span>조회수</span></th>
          <th><span>추천</span></th>
        </tr>
      </thead>
      <tbody class="table-body" :class="$mq">
        <tr>
          <td class="num">12</td>
          <td class="class">자료</td>
          <td class="title"><a class="title-link" href="">새로운 자료 참고하세요</a></td>
          <td class="writer">인포</td>
          <td class="date">2020-04-08</td>
          <td class="view">0</td>
          <td class="like">0</td>
        </tr>
        <tr>
          <td class="num">12</td>
          <td class="class">자료</td>
          <td class="title"><a class="title-link" href="">새로운 자료 참고하세요</a></td>
          <td class="writer">인포</td>
          <td class="date">2020-04-08</td>
          <td class="view">0</td>
          <td class="like">0</td>
        </tr>
        <tr>
          <td class="num">12</td>
          <td class="class">자료</td>
          <td class="title"><a class="title-link" href="">새로운 자료 참고하세요</a></td>
          <td class="writer">인포</td>
          <td class="date">2020-04-08</td>
          <td class="view">0</td>
          <td class="like">0</td>
        </tr>
        <tr>
          <td class="num">12</td>
          <td class="class">자료</td>
          <td class="title"><a class="title-link" href="">새로운 자료 참고하세요</a></td>
          <td class="writer">인포</td>
          <td class="date">2020-04-08</td>
          <td class="view">0</td>
          <td class="like">0</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'Table',
  props: []
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';

.board-table {
  width: 100%;
}
.table-head {
  border-top: 1px solid $white-blue;
  border-bottom: 1px solid $white-blue;

  th {
    height: 35px;
    line-height: 35px;
    font-size: 13px;
    font-weight: bold;
  }
}
.table-body {
  td {
    height: 30px;
    line-height: 30px;
    font-size: 13px;
    border-bottom: 1px solid $white-blue;
    text-align: center;

    .title-link {
      color: black;
      text-align: left;
    }
  }
  tr:hover .title-link {
    color: $classic-blue;
    text-decoration: underline;
  }
  .num, .date, .view {
    color: gray;
  }
  .like {
    color: $classic-blue;
    font-weight: bold;
  }
}
</style>

function fn() {
  var env = karate.env || 'qa';
  var config = {
    env: env,
    baseUrl: karate.properties['karate.baseUrl'] || 'http://localhost:8089',
    publicSiteUrl: 'https://www.dtcc.com',
    defaultHeaders: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    }
  };
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 10000);
  karate.configure('headers', config.defaultHeaders);
  return config;
}

node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/clang-tidyport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/clang-tidyport.git'), string(name: 'PORT_DESCRIPTION', value: 'the llvm clang tidy tool' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}

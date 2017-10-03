
$tag = (git name-rev --name-only --tags HEAD)


if ($tag -like '*undefined*') { 
  Write-Output "This branch is not tagged, skipping deploy"
} else {
  Write-Output "Tag is $tag, Initiating deployment process"
  mkdir .\Tools
  $path = "C:\projects\fret-mapper\Tools"
  $file = "$path\ghr.zip"
  
  
  (New-Object Net.WebClient).DownloadFile('https://github.com/tcnksm/ghr/releases/download/v0.5.4/ghr_v0.5.4_windows_amd64.zip',$file)
  (New-Object -com shell.application).namespace($path).CopyHere((new-object -com shell.application).namespace($file).Items(),16)
  .\Tools\ghr.exe -u divanvisagie $tag target/universal/jdkpackager/bundles
}
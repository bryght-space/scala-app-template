let
  nixos20_03 = {
    "owner" = "NixOS";
    "repo" = "nixpkgs-channels";
    "rev" = "0c0fe6d85b92c4e992e314bd6f9943413af9a309";
    "sha256" = "03q0dzxpd55xmvxzr963hv3nxh57m5d7sgiw5kvhpms1hf8lwd4r";
  };
  nixpkgs20_03_darwin = {
    "owner" = "NixOS";
    "repo" = "nixpkgs-channels";
    "rev" = "0c0fe6d85b92c4e992e314bd6f9943413af9a309";
    "sha256" = "03q0dzxpd55xmvxzr963hv3nxh57m5d7sgiw5kvhpms1hf8lwd4r";
  };
  fetcher = { owner, repo, rev, sha256, ... }: builtins.fetchTarball {
    inherit sha256;
    url = "https://github.com/${owner}/${repo}/tarball/${rev}";
  };
  linuxPackages = fetcher nixos20_03;
  macPackages = fetcher nixpkgs20_03_darwin;
  tmpPackages = (import linuxPackages) {} ;

  onLinux = default: result: if tmpPackages.stdenv.hostPlatform.system == "x86_64-darwin" then default else result;
  onMac = default: result: if tmpPackages.stdenv.hostPlatform.system == "x86_64-darwin" then result else default;
  linuxOrMac = l: m: onLinux m l;

  packages = (import (linuxOrMac linuxPackages macPackages)) {};
in
  packages.sbt

{ pkgs ? import <nixpkgs> {} }:

with pkgs;

mkShell {
  buildInputs = [
    sbt-with-scala-native
    # pkgs.clang_11
    # pkgs.llvmPackages.clangStdenv
    # pkgs.llvmPackages.clang

    # pkgs.llvmPackages.libcxxClang
    # pkgs.llvmPackages.clang
    # # pkgs.glibc
    # pkgs.llvmPackages.libcxxStdenv

    # keep this line if you use bash
    # pkgs.bashInteractive
  ];
}

{ pkgs ? import <nixpkgs> {} }:

with pkgs;
let

sbt-new = (pkgs.buildFHSUserEnv {
   name = "sbt";
   targetPkgs = pkgs: [pkgs.sbt] ++ stdenv.lib.optionals stdenv.isLinux [ zlib ];
   # runScript = "steam-run sbt";
   runScript = "bash";
});

in
pkgs.mkShell {
  buildInputs = [
    sbt-new

    # keep this line if you use bash
    pkgs.bashInteractive
  ];
}

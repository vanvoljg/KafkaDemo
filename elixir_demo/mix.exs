defmodule ElixirDemo.MixProject do
  use Mix.Project

  def project do
    [
      app: :elixir_demo,
      version: "0.1.0",
      elixir: "~> 1.11",
      start_permanent: Mix.env() == :prod,
      deps: deps(),
      aliases: aliases()
    ]
  end

  # Run "mix help compile.app" to learn about applications.
  def application do
    [
      extra_applications: [:logger, :kaffe, :kafka_protocol],
      mod: {ElixirDemo.Application, [Mix.target()]}
    ]
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      # {:dep_from_hexpm, "~> 0.3.0"},
      # {:dep_from_git, git: "https://github.com/elixir-lang/my_dep.git", tag: "0.1.0"}
      # {:kafka_ex, "~> 0.11"}
      {:kaffe, "~> 1.18"},
    ]
  end

  defp aliases do
    [
      producer: [&producer/1, "run"]
    ]
  end

  defp producer(_) do
    Mix.shell().info("producer")
    Mix.target(:producer)
  end
end

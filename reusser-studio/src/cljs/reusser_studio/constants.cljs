(ns reusser-studio.constants)

(defonce target-image "data:image/png;base64,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")

(defonce amazon-image "data:image/png;base64,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")
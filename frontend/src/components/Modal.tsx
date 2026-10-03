import { PropsWithChildren } from "react";
import { createPortal } from "react-dom";

type Props = PropsWithChildren & {
  onClose: () => void;
};

export function Modal({ onClose, children }: Props) {
  return createPortal(
    <div className="modal-backdrop" onClick={onClose}>
      <div
        className="modal"
        role="dialog"
        onClick={(event) => event.stopPropagation()}
      >
        {children}
      </div>
    </div>,
    document.body,
  );
}

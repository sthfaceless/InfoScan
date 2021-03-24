export const MODAL = 'MODAL';

export function openModal(content, options) {
    return {
        type: MODAL,
        state: {
            open: true,
            content,
            options
        }
    }
}

export function closeModal() {
    return {
        type: MODAL,
        state: {
            open: false
        }
    }
}

function loadProducts() {
    fetch('/api/products')
        .then(response => {
            if (!response.ok) throw new Error("Lỗi kết nối server");
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById('product-list');
            tableBody.innerHTML = '';

            data.forEach(p => {
                const productName = p.name || "Không rõ tên";
                const activeStatus = (p.isActive !== undefined) ? p.isActive : false

                const statusHtml = activeStatus
                    ? '<span class="badge bg-success-subtle text-success border border-success status-badge">Đang bán</span>'
                    : '<span class="badge bg-danger-subtle text-danger border border-danger status-badge">Ngừng bán</span>';

                const row = `
                        <tr>
                            <td class="fw-bold text-secondary">#${p.productID}</td>
                            <td class="fw-semibold text-dark">${productName}</td>
                            <td>
                                <span class="badge bg-light text-dark border">${p.order}</span>
                            </td>
                            <td>${statusHtml}</td>
                            <td class="text-center">
                                <button class="btn btn-danger btn-sm" onclick="deleteProduct(${p.productID})">
                                    🗑️ Xóa
                                </button>
                                <button class="btn btn-warning btn-sm me-1" onclick="editProduct(${p.productID})">
                                ✏️ Sửa
                                </button>
                            </td>
                            
                        </tr>
                        `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Lỗi:", error);
            document.getElementById('product-list').innerHTML =
                `<tr><td colspan="4" class="text-danger text-center">❌ Lỗi: ${error.message}</td></tr>`;
        });
}

window.onload = loadProducts;

function deleteProduct(id) {
    if (confirm(`có chắc chắn muốn xóa sản phẩm #${id} không?`)) {
        fetch(`/api/products/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    alert("Đã xóa xong!");
                    loadProducts();
                } else {
                    alert("Xóa thất bại");
                }
            })
            .catch(error => console.error("Lỗi xóa:", error));
    }
}

function saveProduct() {
    const productData = {
        name: document.getElementById('newName').value,
        order: parseInt(document.getElementById('newOrder').value),
        isActive: document.getElementById('newActive').checked
    };

    fetch('/api/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(productData)
    })
        .then(response => {
            if (response.ok) {
                alert("Đã thêm sản phẩm thành công!");
                const modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
                modal.hide();

                document.getElementById('addForm').reset();
                loadProducts();
            } else {
                alert("Lỗi khi thêm sản phẩm!");
            }
        });
}


function editProduct(id) {
    fetch(`/api/products/${id}`)
        .then(res => res.json())
        .then(p => {
            document.getElementById('editId').value = p.productID;
            document.getElementById('editName').value = p.name;
            document.getElementById('editOrder').value = p.order;
            document.getElementById('editActive').checked = p.isActive;

            const modal = new bootstrap.Modal(document.getElementById('editModal'));
            modal.show();
        });
}

function updateProduct() {
    const id = document.getElementById('editId').value;
    const data = {
        name: document.getElementById('editName').value,
        order: parseInt(document.getElementById('editOrder').value),
        isActive: document.getElementById('editActive').checked
    };

    fetch(`/api/products/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (res.ok) {
                alert("Cập nhật thành công!");
                bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
                loadProducts();
            }
        });
}
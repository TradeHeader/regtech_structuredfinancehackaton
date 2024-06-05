package cdm.event.common;

import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.TransferState.TransferStateBuilder;
import cdm.event.common.TransferState.TransferStateBuilderImpl;
import cdm.event.common.TransferState.TransferStateImpl;
import cdm.event.common.TransferStatusEnum;
import cdm.event.common.meta.TransferStateMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines the fundamental financial information associated with a Transfer event. Each TransferState specifies where a Transfer is in its life-cycle. TransferState is a root type and as such, can be created independently to any other CDM data type, but can also be used as part of the CDM Event Model.
 * @version ${project.version}
 */
@RosettaDataType(value="TransferState", builder=TransferState.TransferStateBuilderImpl.class, version="${project.version}")
public interface TransferState extends RosettaModelObject, GlobalKey {

	TransferStateMeta metaData = new TransferStateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the Transfer that has been effected by a business or life-cycle event.
	 */
	Transfer getTransfer();
	/**
	 * Represents the State of the Transfer through its life-cycle.
	 */
	TransferStatusEnum getTransferStatus();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	TransferState build();
	
	TransferState.TransferStateBuilder toBuilder();
	
	static TransferState.TransferStateBuilder builder() {
		return new TransferState.TransferStateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransferState> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransferState> getType() {
		return TransferState.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("transfer"), processor, Transfer.class, getTransfer());
		processor.processBasic(path.newSubPath("transferStatus"), TransferStatusEnum.class, getTransferStatus(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransferStateBuilder extends TransferState, RosettaModelObjectBuilder {
		Transfer.TransferBuilder getOrCreateTransfer();
		Transfer.TransferBuilder getTransfer();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		TransferState.TransferStateBuilder setTransfer(Transfer transfer);
		TransferState.TransferStateBuilder setTransferStatus(TransferStatusEnum transferStatus);
		TransferState.TransferStateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("transfer"), processor, Transfer.TransferBuilder.class, getTransfer());
			processor.processBasic(path.newSubPath("transferStatus"), TransferStatusEnum.class, getTransferStatus(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		TransferState.TransferStateBuilder prune();
	}

	/*********************** Immutable Implementation of TransferState  ***********************/
	class TransferStateImpl implements TransferState {
		private final Transfer transfer;
		private final TransferStatusEnum transferStatus;
		private final MetaFields meta;
		
		protected TransferStateImpl(TransferState.TransferStateBuilder builder) {
			this.transfer = ofNullable(builder.getTransfer()).map(f->f.build()).orElse(null);
			this.transferStatus = builder.getTransferStatus();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("transfer")
		public Transfer getTransfer() {
			return transfer;
		}
		
		@Override
		@RosettaAttribute("transferStatus")
		public TransferStatusEnum getTransferStatus() {
			return transferStatus;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public TransferState build() {
			return this;
		}
		
		@Override
		public TransferState.TransferStateBuilder toBuilder() {
			TransferState.TransferStateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransferState.TransferStateBuilder builder) {
			ofNullable(getTransfer()).ifPresent(builder::setTransfer);
			ofNullable(getTransferStatus()).ifPresent(builder::setTransferStatus);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferState _that = getType().cast(o);
		
			if (!Objects.equals(transfer, _that.getTransfer())) return false;
			if (!Objects.equals(transferStatus, _that.getTransferStatus())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transfer != null ? transfer.hashCode() : 0);
			_result = 31 * _result + (transferStatus != null ? transferStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferState {" +
				"transfer=" + this.transfer + ", " +
				"transferStatus=" + this.transferStatus + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of TransferState  ***********************/
	class TransferStateBuilderImpl implements TransferState.TransferStateBuilder, GlobalKeyBuilder {
	
		protected Transfer.TransferBuilder transfer;
		protected TransferStatusEnum transferStatus;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public TransferStateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("transfer")
		public Transfer.TransferBuilder getTransfer() {
			return transfer;
		}
		
		@Override
		public Transfer.TransferBuilder getOrCreateTransfer() {
			Transfer.TransferBuilder result;
			if (transfer!=null) {
				result = transfer;
			}
			else {
				result = transfer = Transfer.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("transferStatus")
		public TransferStatusEnum getTransferStatus() {
			return transferStatus;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("transfer")
		public TransferState.TransferStateBuilder setTransfer(Transfer transfer) {
			this.transfer = transfer==null?null:transfer.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("transferStatus")
		public TransferState.TransferStateBuilder setTransferStatus(TransferStatusEnum transferStatus) {
			this.transferStatus = transferStatus==null?null:transferStatus;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public TransferState.TransferStateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public TransferState build() {
			return new TransferState.TransferStateImpl(this);
		}
		
		@Override
		public TransferState.TransferStateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferState.TransferStateBuilder prune() {
			if (transfer!=null && !transfer.prune().hasData()) transfer = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTransfer()!=null && getTransfer().hasData()) return true;
			if (getTransferStatus()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferState.TransferStateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransferState.TransferStateBuilder o = (TransferState.TransferStateBuilder) other;
			
			merger.mergeRosetta(getTransfer(), o.getTransfer(), this::setTransfer);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getTransferStatus(), o.getTransferStatus(), this::setTransferStatus);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferState _that = getType().cast(o);
		
			if (!Objects.equals(transfer, _that.getTransfer())) return false;
			if (!Objects.equals(transferStatus, _that.getTransferStatus())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transfer != null ? transfer.hashCode() : 0);
			_result = 31 * _result + (transferStatus != null ? transferStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferStateBuilder {" +
				"transfer=" + this.transfer + ", " +
				"transferStatus=" + this.transferStatus + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

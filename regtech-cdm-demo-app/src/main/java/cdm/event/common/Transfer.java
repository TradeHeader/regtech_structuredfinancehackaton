package cdm.event.common;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.metafields.FieldWithMetaIdentifier;
import cdm.base.staticdata.identifier.metafields.FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.Reset;
import cdm.event.common.SettlementOrigin;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.event.common.Transfer.TransferBuilderImpl;
import cdm.event.common.Transfer.TransferImpl;
import cdm.event.common.TransferBase;
import cdm.event.common.TransferBase.TransferBaseBuilder;
import cdm.event.common.TransferBase.TransferBaseBuilderImpl;
import cdm.event.common.TransferBase.TransferBaseImpl;
import cdm.event.common.TransferExpression;
import cdm.event.common.meta.TransferMeta;
import cdm.observable.asset.Observable;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines the movement of cash, securities or commodities between two parties on a date.
 * @version ${project.version}
 */
@RosettaDataType(value="Transfer", builder=Transfer.TransferBuilderImpl.class, version="${project.version}")
public interface Transfer extends TransferBase {

	TransferMeta metaData = new TransferMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the origin to the transfer as a reference for lineage purposes, whether it originated from trade level settlement terms or from payment terms on an economic payout.
	 */
	SettlementOrigin getSettlementOrigin();
	/**
	 * Represents the reset and observation values that were used to determine the transfer amount.
	 */
	Reset getResetOrigin();
	/**
	 * Specifies a transfer expression (cash price, performance amount, scheduled payment amount, etc.) to define the nature of the transfer amount and its source.
	 */
	TransferExpression getTransferExpression();

	/*********************** Build Methods  ***********************/
	Transfer build();
	
	Transfer.TransferBuilder toBuilder();
	
	static Transfer.TransferBuilder builder() {
		return new Transfer.TransferBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Transfer> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Transfer> getType() {
		return Transfer.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaIdentifier.class, getIdentifier());
		processRosetta(path.newSubPath("quantity"), processor, NonNegativeQuantity.class, getQuantity());
		processRosetta(path.newSubPath("observable"), processor, Observable.class, getObservable());
		processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrAdjustedOrRelativeDate.class, getSettlementDate());
		processRosetta(path.newSubPath("settlementOrigin"), processor, SettlementOrigin.class, getSettlementOrigin());
		processRosetta(path.newSubPath("resetOrigin"), processor, Reset.class, getResetOrigin());
		processRosetta(path.newSubPath("transferExpression"), processor, TransferExpression.class, getTransferExpression());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransferBuilder extends Transfer, TransferBase.TransferBaseBuilder, RosettaModelObjectBuilder {
		SettlementOrigin.SettlementOriginBuilder getOrCreateSettlementOrigin();
		SettlementOrigin.SettlementOriginBuilder getSettlementOrigin();
		Reset.ResetBuilder getOrCreateResetOrigin();
		Reset.ResetBuilder getResetOrigin();
		TransferExpression.TransferExpressionBuilder getOrCreateTransferExpression();
		TransferExpression.TransferExpressionBuilder getTransferExpression();
		Transfer.TransferBuilder addIdentifier(FieldWithMetaIdentifier identifier0);
		Transfer.TransferBuilder addIdentifier(FieldWithMetaIdentifier identifier1, int _idx);
		Transfer.TransferBuilder addIdentifierValue(Identifier identifier2);
		Transfer.TransferBuilder addIdentifierValue(Identifier identifier3, int _idx);
		Transfer.TransferBuilder addIdentifier(List<? extends FieldWithMetaIdentifier> identifier4);
		Transfer.TransferBuilder setIdentifier(List<? extends FieldWithMetaIdentifier> identifier5);
		Transfer.TransferBuilder addIdentifierValue(List<? extends Identifier> identifier6);
		Transfer.TransferBuilder setIdentifierValue(List<? extends Identifier> identifier7);
		Transfer.TransferBuilder setQuantity(NonNegativeQuantity quantity);
		Transfer.TransferBuilder setObservable(Observable observable);
		Transfer.TransferBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver);
		Transfer.TransferBuilder setSettlementDate(AdjustableOrAdjustedOrRelativeDate settlementDate);
		Transfer.TransferBuilder setSettlementOrigin(SettlementOrigin settlementOrigin);
		Transfer.TransferBuilder setResetOrigin(Reset resetOrigin);
		Transfer.TransferBuilder setTransferExpression(TransferExpression transferExpression);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("quantity"), processor, NonNegativeQuantity.NonNegativeQuantityBuilder.class, getQuantity());
			processRosetta(path.newSubPath("observable"), processor, Observable.ObservableBuilder.class, getObservable());
			processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder.class, getSettlementDate());
			processRosetta(path.newSubPath("settlementOrigin"), processor, SettlementOrigin.SettlementOriginBuilder.class, getSettlementOrigin());
			processRosetta(path.newSubPath("resetOrigin"), processor, Reset.ResetBuilder.class, getResetOrigin());
			processRosetta(path.newSubPath("transferExpression"), processor, TransferExpression.TransferExpressionBuilder.class, getTransferExpression());
		}
		

		Transfer.TransferBuilder prune();
	}

	/*********************** Immutable Implementation of Transfer  ***********************/
	class TransferImpl extends TransferBase.TransferBaseImpl implements Transfer {
		private final SettlementOrigin settlementOrigin;
		private final Reset resetOrigin;
		private final TransferExpression transferExpression;
		
		protected TransferImpl(Transfer.TransferBuilder builder) {
			super(builder);
			this.settlementOrigin = ofNullable(builder.getSettlementOrigin()).map(f->f.build()).orElse(null);
			this.resetOrigin = ofNullable(builder.getResetOrigin()).map(f->f.build()).orElse(null);
			this.transferExpression = ofNullable(builder.getTransferExpression()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("settlementOrigin")
		public SettlementOrigin getSettlementOrigin() {
			return settlementOrigin;
		}
		
		@Override
		@RosettaAttribute("resetOrigin")
		public Reset getResetOrigin() {
			return resetOrigin;
		}
		
		@Override
		@RosettaAttribute("transferExpression")
		public TransferExpression getTransferExpression() {
			return transferExpression;
		}
		
		@Override
		public Transfer build() {
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder toBuilder() {
			Transfer.TransferBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Transfer.TransferBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getSettlementOrigin()).ifPresent(builder::setSettlementOrigin);
			ofNullable(getResetOrigin()).ifPresent(builder::setResetOrigin);
			ofNullable(getTransferExpression()).ifPresent(builder::setTransferExpression);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Transfer _that = getType().cast(o);
		
			if (!Objects.equals(settlementOrigin, _that.getSettlementOrigin())) return false;
			if (!Objects.equals(resetOrigin, _that.getResetOrigin())) return false;
			if (!Objects.equals(transferExpression, _that.getTransferExpression())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (settlementOrigin != null ? settlementOrigin.hashCode() : 0);
			_result = 31 * _result + (resetOrigin != null ? resetOrigin.hashCode() : 0);
			_result = 31 * _result + (transferExpression != null ? transferExpression.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Transfer {" +
				"settlementOrigin=" + this.settlementOrigin + ", " +
				"resetOrigin=" + this.resetOrigin + ", " +
				"transferExpression=" + this.transferExpression +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Transfer  ***********************/
	class TransferBuilderImpl extends TransferBase.TransferBaseBuilderImpl  implements Transfer.TransferBuilder {
	
		protected SettlementOrigin.SettlementOriginBuilder settlementOrigin;
		protected Reset.ResetBuilder resetOrigin;
		protected TransferExpression.TransferExpressionBuilder transferExpression;
	
		public TransferBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementOrigin")
		public SettlementOrigin.SettlementOriginBuilder getSettlementOrigin() {
			return settlementOrigin;
		}
		
		@Override
		public SettlementOrigin.SettlementOriginBuilder getOrCreateSettlementOrigin() {
			SettlementOrigin.SettlementOriginBuilder result;
			if (settlementOrigin!=null) {
				result = settlementOrigin;
			}
			else {
				result = settlementOrigin = SettlementOrigin.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("resetOrigin")
		public Reset.ResetBuilder getResetOrigin() {
			return resetOrigin;
		}
		
		@Override
		public Reset.ResetBuilder getOrCreateResetOrigin() {
			Reset.ResetBuilder result;
			if (resetOrigin!=null) {
				result = resetOrigin;
			}
			else {
				result = resetOrigin = Reset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("transferExpression")
		public TransferExpression.TransferExpressionBuilder getTransferExpression() {
			return transferExpression;
		}
		
		@Override
		public TransferExpression.TransferExpressionBuilder getOrCreateTransferExpression() {
			TransferExpression.TransferExpressionBuilder result;
			if (transferExpression!=null) {
				result = transferExpression;
			}
			else {
				result = transferExpression = TransferExpression.builder();
			}
			
			return result;
		}
	
		@Override
		public Transfer.TransferBuilder addIdentifier(FieldWithMetaIdentifier identifier) {
			if (identifier!=null) this.identifier.add(identifier.toBuilder());
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder addIdentifier(FieldWithMetaIdentifier identifier, int _idx) {
			getIndex(this.identifier, _idx, () -> identifier.toBuilder());
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder addIdentifierValue(Identifier identifier) {
			this.getOrCreateIdentifier(-1).setValue(identifier.toBuilder());
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder addIdentifierValue(Identifier identifier, int _idx) {
			this.getOrCreateIdentifier(_idx).setValue(identifier.toBuilder());
			return this;
		}
		@Override 
		public Transfer.TransferBuilder addIdentifier(List<? extends FieldWithMetaIdentifier> identifiers) {
			if (identifiers != null) {
				for (FieldWithMetaIdentifier toAdd : identifiers) {
					this.identifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("identifier")
		public Transfer.TransferBuilder setIdentifier(List<? extends FieldWithMetaIdentifier> identifiers) {
			if (identifiers == null)  {
				this.identifier = new ArrayList<>();
			}
			else {
				this.identifier = identifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder addIdentifierValue(List<? extends Identifier> identifiers) {
			if (identifiers != null) {
				for (Identifier toAdd : identifiers) {
					this.addIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Transfer.TransferBuilder setIdentifierValue(List<? extends Identifier> identifiers) {
			this.identifier.clear();
			if (identifiers!=null) {
				identifiers.forEach(this::addIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("quantity")
		public Transfer.TransferBuilder setQuantity(NonNegativeQuantity quantity) {
			this.quantity = quantity==null?null:quantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observable")
		public Transfer.TransferBuilder setObservable(Observable observable) {
			this.observable = observable==null?null:observable.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public Transfer.TransferBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public Transfer.TransferBuilder setSettlementDate(AdjustableOrAdjustedOrRelativeDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementOrigin")
		public Transfer.TransferBuilder setSettlementOrigin(SettlementOrigin settlementOrigin) {
			this.settlementOrigin = settlementOrigin==null?null:settlementOrigin.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("resetOrigin")
		public Transfer.TransferBuilder setResetOrigin(Reset resetOrigin) {
			this.resetOrigin = resetOrigin==null?null:resetOrigin.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("transferExpression")
		public Transfer.TransferBuilder setTransferExpression(TransferExpression transferExpression) {
			this.transferExpression = transferExpression==null?null:transferExpression.toBuilder();
			return this;
		}
		
		@Override
		public Transfer build() {
			return new Transfer.TransferImpl(this);
		}
		
		@Override
		public Transfer.TransferBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Transfer.TransferBuilder prune() {
			super.prune();
			if (settlementOrigin!=null && !settlementOrigin.prune().hasData()) settlementOrigin = null;
			if (resetOrigin!=null && !resetOrigin.prune().hasData()) resetOrigin = null;
			if (transferExpression!=null && !transferExpression.prune().hasData()) transferExpression = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getSettlementOrigin()!=null && getSettlementOrigin().hasData()) return true;
			if (getResetOrigin()!=null && getResetOrigin().hasData()) return true;
			if (getTransferExpression()!=null && getTransferExpression().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Transfer.TransferBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Transfer.TransferBuilder o = (Transfer.TransferBuilder) other;
			
			merger.mergeRosetta(getSettlementOrigin(), o.getSettlementOrigin(), this::setSettlementOrigin);
			merger.mergeRosetta(getResetOrigin(), o.getResetOrigin(), this::setResetOrigin);
			merger.mergeRosetta(getTransferExpression(), o.getTransferExpression(), this::setTransferExpression);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			Transfer _that = getType().cast(o);
		
			if (!Objects.equals(settlementOrigin, _that.getSettlementOrigin())) return false;
			if (!Objects.equals(resetOrigin, _that.getResetOrigin())) return false;
			if (!Objects.equals(transferExpression, _that.getTransferExpression())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (settlementOrigin != null ? settlementOrigin.hashCode() : 0);
			_result = 31 * _result + (resetOrigin != null ? resetOrigin.hashCode() : 0);
			_result = 31 * _result + (transferExpression != null ? transferExpression.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferBuilder {" +
				"settlementOrigin=" + this.settlementOrigin + ", " +
				"resetOrigin=" + this.resetOrigin + ", " +
				"transferExpression=" + this.transferExpression +
			'}' + " " + super.toString();
		}
	}
}

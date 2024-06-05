package cdm.event.common;

import cdm.base.math.Quantity;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.CalculateTransferInstruction.CalculateTransferInstructionBuilder;
import cdm.event.common.CalculateTransferInstruction.CalculateTransferInstructionBuilderImpl;
import cdm.event.common.CalculateTransferInstruction.CalculateTransferInstructionImpl;
import cdm.event.common.Reset;
import cdm.event.common.TradeState;
import cdm.event.common.meta.CalculateTransferInstructionMeta;
import cdm.product.template.Payout;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
import cdm.product.template.metafields.ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines the tradeState or payout on which to create a Transfer along with all necessary resets.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculateTransferInstruction", builder=CalculateTransferInstruction.CalculateTransferInstructionBuilderImpl.class, version="${project.version}")
public interface CalculateTransferInstruction extends RosettaModelObject {

	CalculateTransferInstructionMeta metaData = new CalculateTransferInstructionMeta();

	/*********************** Getter Methods  ***********************/
	TradeState getTradeState();
	ReferenceWithMetaPayout getPayout();
	List<? extends Reset> getResets();
	PayerReceiver getPayerReceiver();
	/**
	 * Specifies quantity amount returned if not the full amount from the TradeState, e.g. partial return
	 */
	Quantity getQuantity();
	Date getDate();

	/*********************** Build Methods  ***********************/
	CalculateTransferInstruction build();
	
	CalculateTransferInstruction.CalculateTransferInstructionBuilder toBuilder();
	
	static CalculateTransferInstruction.CalculateTransferInstructionBuilder builder() {
		return new CalculateTransferInstruction.CalculateTransferInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculateTransferInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculateTransferInstruction> getType() {
		return CalculateTransferInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tradeState"), processor, TradeState.class, getTradeState());
		processRosetta(path.newSubPath("payout"), processor, ReferenceWithMetaPayout.class, getPayout());
		processRosetta(path.newSubPath("resets"), processor, Reset.class, getResets());
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("quantity"), processor, Quantity.class, getQuantity());
		processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculateTransferInstructionBuilder extends CalculateTransferInstruction, RosettaModelObjectBuilder {
		TradeState.TradeStateBuilder getOrCreateTradeState();
		TradeState.TradeStateBuilder getTradeState();
		ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder getOrCreatePayout();
		ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder getPayout();
		Reset.ResetBuilder getOrCreateResets(int _index);
		List<? extends Reset.ResetBuilder> getResets();
		PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver();
		PayerReceiver.PayerReceiverBuilder getPayerReceiver();
		Quantity.QuantityBuilder getOrCreateQuantity();
		Quantity.QuantityBuilder getQuantity();
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setTradeState(TradeState tradeState);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayout(ReferenceWithMetaPayout payout0);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayoutValue(Payout payout1);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(Reset resets0);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(Reset resets1, int _idx);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(List<? extends Reset> resets2);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setResets(List<? extends Reset> resets3);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayerReceiver(PayerReceiver payerReceiver);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setQuantity(Quantity quantity);
		CalculateTransferInstruction.CalculateTransferInstructionBuilder setDate(Date date);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tradeState"), processor, TradeState.TradeStateBuilder.class, getTradeState());
			processRosetta(path.newSubPath("payout"), processor, ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder.class, getPayout());
			processRosetta(path.newSubPath("resets"), processor, Reset.ResetBuilder.class, getResets());
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("quantity"), processor, Quantity.QuantityBuilder.class, getQuantity());
			processor.processBasic(path.newSubPath("date"), Date.class, getDate(), this);
		}
		

		CalculateTransferInstruction.CalculateTransferInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of CalculateTransferInstruction  ***********************/
	class CalculateTransferInstructionImpl implements CalculateTransferInstruction {
		private final TradeState tradeState;
		private final ReferenceWithMetaPayout payout;
		private final List<? extends Reset> resets;
		private final PayerReceiver payerReceiver;
		private final Quantity quantity;
		private final Date date;
		
		protected CalculateTransferInstructionImpl(CalculateTransferInstruction.CalculateTransferInstructionBuilder builder) {
			this.tradeState = ofNullable(builder.getTradeState()).map(f->f.build()).orElse(null);
			this.payout = ofNullable(builder.getPayout()).map(f->f.build()).orElse(null);
			this.resets = ofNullable(builder.getResets()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.quantity = ofNullable(builder.getQuantity()).map(f->f.build()).orElse(null);
			this.date = builder.getDate();
		}
		
		@Override
		@RosettaAttribute("tradeState")
		public TradeState getTradeState() {
			return tradeState;
		}
		
		@Override
		@RosettaAttribute("payout")
		public ReferenceWithMetaPayout getPayout() {
			return payout;
		}
		
		@Override
		@RosettaAttribute("resets")
		public List<? extends Reset> getResets() {
			return resets;
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("quantity")
		public Quantity getQuantity() {
			return quantity;
		}
		
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
		@Override
		public CalculateTransferInstruction build() {
			return this;
		}
		
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder toBuilder() {
			CalculateTransferInstruction.CalculateTransferInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculateTransferInstruction.CalculateTransferInstructionBuilder builder) {
			ofNullable(getTradeState()).ifPresent(builder::setTradeState);
			ofNullable(getPayout()).ifPresent(builder::setPayout);
			ofNullable(getResets()).ifPresent(builder::setResets);
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
			ofNullable(getDate()).ifPresent(builder::setDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculateTransferInstruction _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!Objects.equals(payout, _that.getPayout())) return false;
			if (!ListEquals.listEquals(resets, _that.getResets())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (payout != null ? payout.hashCode() : 0);
			_result = 31 * _result + (resets != null ? resets.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculateTransferInstruction {" +
				"tradeState=" + this.tradeState + ", " +
				"payout=" + this.payout + ", " +
				"resets=" + this.resets + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"quantity=" + this.quantity + ", " +
				"date=" + this.date +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculateTransferInstruction  ***********************/
	class CalculateTransferInstructionBuilderImpl implements CalculateTransferInstruction.CalculateTransferInstructionBuilder {
	
		protected TradeState.TradeStateBuilder tradeState;
		protected ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder payout;
		protected List<Reset.ResetBuilder> resets = new ArrayList<>();
		protected PayerReceiver.PayerReceiverBuilder payerReceiver;
		protected Quantity.QuantityBuilder quantity;
		protected Date date;
	
		public CalculateTransferInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tradeState")
		public TradeState.TradeStateBuilder getTradeState() {
			return tradeState;
		}
		
		@Override
		public TradeState.TradeStateBuilder getOrCreateTradeState() {
			TradeState.TradeStateBuilder result;
			if (tradeState!=null) {
				result = tradeState;
			}
			else {
				result = tradeState = TradeState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("payout")
		public ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder getPayout() {
			return payout;
		}
		
		@Override
		public ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder getOrCreatePayout() {
			ReferenceWithMetaPayout.ReferenceWithMetaPayoutBuilder result;
			if (payout!=null) {
				result = payout;
			}
			else {
				result = payout = ReferenceWithMetaPayout.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("resets")
		public List<? extends Reset.ResetBuilder> getResets() {
			return resets;
		}
		
		public Reset.ResetBuilder getOrCreateResets(int _index) {
		
			if (resets==null) {
				this.resets = new ArrayList<>();
			}
			Reset.ResetBuilder result;
			return getIndex(resets, _index, () -> {
						Reset.ResetBuilder newResets = Reset.builder();
						return newResets;
					});
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver.PayerReceiverBuilder getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver() {
			PayerReceiver.PayerReceiverBuilder result;
			if (payerReceiver!=null) {
				result = payerReceiver;
			}
			else {
				result = payerReceiver = PayerReceiver.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("quantity")
		public Quantity.QuantityBuilder getQuantity() {
			return quantity;
		}
		
		@Override
		public Quantity.QuantityBuilder getOrCreateQuantity() {
			Quantity.QuantityBuilder result;
			if (quantity!=null) {
				result = quantity;
			}
			else {
				result = quantity = Quantity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("date")
		public Date getDate() {
			return date;
		}
		
	
		@Override
		@RosettaAttribute("tradeState")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setTradeState(TradeState tradeState) {
			this.tradeState = tradeState==null?null:tradeState.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payout")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayout(ReferenceWithMetaPayout payout) {
			this.payout = payout==null?null:payout.toBuilder();
			return this;
		}
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayoutValue(Payout payout) {
			this.getOrCreatePayout().setValue(payout);
			return this;
		}
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(Reset resets) {
			if (resets!=null) this.resets.add(resets.toBuilder());
			return this;
		}
		
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(Reset resets, int _idx) {
			getIndex(this.resets, _idx, () -> resets.toBuilder());
			return this;
		}
		@Override 
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder addResets(List<? extends Reset> resetss) {
			if (resetss != null) {
				for (Reset toAdd : resetss) {
					this.resets.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("resets")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setResets(List<? extends Reset> resetss) {
			if (resetss == null)  {
				this.resets = new ArrayList<>();
			}
			else {
				this.resets = resetss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("quantity")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setQuantity(Quantity quantity) {
			this.quantity = quantity==null?null:quantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("date")
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder setDate(Date date) {
			this.date = date==null?null:date;
			return this;
		}
		
		@Override
		public CalculateTransferInstruction build() {
			return new CalculateTransferInstruction.CalculateTransferInstructionImpl(this);
		}
		
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder prune() {
			if (tradeState!=null && !tradeState.prune().hasData()) tradeState = null;
			if (payout!=null && !payout.prune().hasData()) payout = null;
			resets = resets.stream().filter(b->b!=null).<Reset.ResetBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			if (quantity!=null && !quantity.prune().hasData()) quantity = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTradeState()!=null && getTradeState().hasData()) return true;
			if (getPayout()!=null && getPayout().hasData()) return true;
			if (getResets()!=null && getResets().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getQuantity()!=null && getQuantity().hasData()) return true;
			if (getDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculateTransferInstruction.CalculateTransferInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculateTransferInstruction.CalculateTransferInstructionBuilder o = (CalculateTransferInstruction.CalculateTransferInstructionBuilder) other;
			
			merger.mergeRosetta(getTradeState(), o.getTradeState(), this::setTradeState);
			merger.mergeRosetta(getPayout(), o.getPayout(), this::setPayout);
			merger.mergeRosetta(getResets(), o.getResets(), this::getOrCreateResets);
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::setQuantity);
			
			merger.mergeBasic(getDate(), o.getDate(), this::setDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculateTransferInstruction _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!Objects.equals(payout, _that.getPayout())) return false;
			if (!ListEquals.listEquals(resets, _that.getResets())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(date, _that.getDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (payout != null ? payout.hashCode() : 0);
			_result = 31 * _result + (resets != null ? resets.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (date != null ? date.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculateTransferInstructionBuilder {" +
				"tradeState=" + this.tradeState + ", " +
				"payout=" + this.payout + ", " +
				"resets=" + this.resets + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"quantity=" + this.quantity + ", " +
				"date=" + this.date +
			'}';
		}
	}
}
